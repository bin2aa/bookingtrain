package com.example.bookingtrain.repository;

import com.example.bookingtrain.DTO.InforReservationDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public class InfoReservationRepository {

        private final JdbcTemplate jdbcTemplate;

        public InfoReservationRepository(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        public Page<InforReservationDTO> findAvailableSeatsByStationsWithPagination(
                        Long stationDepartureId,
                        Long stationArrivalId,
                        String dateStart,
                        Pageable pageable) {

                String sql = "SELECT " +
                                "tr.trainId AS trainId, " +
                                "sc.scheduleId AS scheduleId, " +
                                "sd.stationName AS Departure, " +
                                "sa.stationName AS Arrival, " +
                                "tr.trainName AS trainCode, " +
                                "DATE(sc.startDeparture) AS DepartureDate, " +
                                "TIME(sc.startDeparture) AS DepartureTime, " +
                                "DATE(sc.endDeparture) AS ArrivalDate, " +
                                "TIME(sc.endDeparture) AS ArrivalTime, " +
                                "TIMEDIFF(sc.endDeparture, sc.startDeparture) AS totalTime, " +
                                "sdc.stationName AS ScheduleDeparture, " +
                                "sac.stationName AS ScheduleArrival " +
                                "FROM schedules sc " +
                                "JOIN trains tr ON sc.trainId = tr.trainId " +
                                "JOIN routes ro ON ro.trainId = tr.trainId " +
                                "JOIN stations sd ON sd.stationId = ro.stationDepartureId " +
                                "JOIN stations sa ON sa.stationId = ro.stationArrivalId " +
                                "JOIN stations sdc ON sdc.stationId = sc.stationDepartureId " +
                                "JOIN stations sac ON sac.stationId = sc.stationArrivalId " +
                                "WHERE ro.stationDepartureId = ? " +
                                "AND ro.stationArrivalId = ? " +
                                "AND DATE(sc.startDeparture) = ? " +
                                "LIMIT ? OFFSET ?";

                // Lấy tổng số lượng bản ghi cho phân trang
                String countSql = "SELECT COUNT(*) FROM schedules sc " +
                                "JOIN trains tr ON sc.trainId = tr.trainId " +
                                "JOIN routes ro ON ro.trainId = tr.trainId " +
                                "WHERE ro.stationDepartureId = ? " +
                                "AND ro.stationArrivalId = ? " +
                                "AND DATE(sc.startDeparture) = ?";

                int total = jdbcTemplate.queryForObject(countSql, Integer.class,
                                stationDepartureId, stationArrivalId, dateStart);

                List<InforReservationDTO> tickets = jdbcTemplate.query(sql, (rs, rowNum) -> {
                        InforReservationDTO ticketInfo = new InforReservationDTO();
                        ticketInfo.setTrainId(rs.getInt("trainId"));
                        ticketInfo.setScheduleId(rs.getInt("scheduleId"));
                        ticketInfo.setStationDeparture(rs.getString("Departure"));
                        ticketInfo.setStationArrival(rs.getString("Arrival"));
                        ticketInfo.setTrainCode(rs.getString("trainCode"));
                        ticketInfo.setDateDepartureFormatted(rs.getString("DepartureDate"));
                        ticketInfo.setTimeDepartureFormatted(rs.getString("DepartureTime"));
                        ticketInfo.setDateArrivalFormatted(rs.getString("ArrivalDate"));
                        ticketInfo.setTimeArrivalFormatted(rs.getString("ArrivalTime"));
                        ticketInfo.setScheduleDeparture(rs.getString("ScheduleDeparture"));
                        ticketInfo.setScheduleArrival(rs.getString("ScheduleArrival"));
                        ticketInfo.setTotalTime(rs.getString("TotalTime"));
                        return ticketInfo;
                }, stationDepartureId, stationArrivalId, dateStart,
                                pageable.getPageSize(), pageable.getOffset());

                return new PageImpl<>(tickets, pageable, total);
        }
}