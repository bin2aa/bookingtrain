package com.example.bookingtrain.repository;

import com.example.bookingtrain.DTO.InforReservationDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class InfoReservationRepository {
        private final JdbcTemplate jdbcTemplate;
        private static final Logger logger = LoggerFactory.getLogger(InfoReservationRepository.class);

        public InfoReservationRepository(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        public Page<InforReservationDTO> findAvailableSeatsByStationsWithPagination(
                        Long stationDepartureId,
                        Long stationArrivalId,
                        String startDeparture,
                        Pageable pageable) {

                String sql = "SELECT tr.trainId, sc.scheduleId, sd.stationName AS departureStation, " +
                                "sa.stationName AS arrivalStation, tr.trainName AS trainCode, " +
                                "tr.image AS trainImage, DATE(sc.startDeparture) AS departureDate, " +
                                "TIME(sc.startDeparture) AS departureTime, DATE(sc.endDeparture) AS arrivalDate, " +
                                "TIME(sc.endDeparture) AS arrivalTime, TIMEDIFF(sc.endDeparture, sc.startDeparture) AS journeyDuration, "
                                +
                                "ro.routeName FROM schedules sc " +
                                "JOIN trains tr ON sc.trainId = tr.trainId " +
                                "JOIN routes ro ON ro.routeId = sc.routeId " +
                                "JOIN stations sd ON sd.stationId = sc.stationDepartureId " +
                                "JOIN stations sa ON sa.stationId = sc.stationArrivalId " +
                                "WHERE sc.stationDepartureId = ? " +
                                "AND sc.stationArrivalId = ? " +
                                "AND DATE(sc.startDeparture) = ? " +
                                "AND sc.statusSchedule = 1 " +
                                "ORDER BY sc.startDeparture";

                List<InforReservationDTO> tickets = jdbcTemplate.query(sql, (rs, rowNum) -> {
                        InforReservationDTO ticket = new InforReservationDTO();
                        ticket.setTrainId(rs.getInt("trainId"));
                        ticket.setScheduleId(rs.getInt("scheduleId"));
                        ticket.setStationDeparture(rs.getString("departureStation"));
                        ticket.setStationArrival(rs.getString("arrivalStation"));
                        ticket.setTrainCode(rs.getString("trainCode"));
                        ticket.setImage(rs.getString("trainImage"));
                        ticket.setDateDepartureFormatted(rs.getString("departureDate"));
                        ticket.setTimeDepartureFormatted(rs.getString("departureTime"));
                        ticket.setDateArrivalFormatted(rs.getString("arrivalDate"));
                        ticket.setTimeArrivalFormatted(rs.getString("arrivalTime"));
                        ticket.setTotalTime(rs.getString("journeyDuration"));
                        ticket.setRouteName(rs.getString("routeName"));
                        System.out.println("ScheduleId: " + ticket.getScheduleId());
                        return ticket;
                }, stationDepartureId, stationArrivalId, startDeparture);

                return new PageImpl<>(tickets, pageable, tickets.size());
        }
}
// SELECT
// tr.trainId,
// sc.scheduleId,
// sd.stationName AS departureStation,
// sa.stationName AS arrivalStation,
// tr.trainName AS trainCode,
// tr.image AS trainImage,
// DATE(sc.startDeparture) AS departureDate,
// TIME(sc.startDeparture) AS departureTime,
// DATE(sc.endDeparture) AS arrivalDate,
// TIME(sc.endDeparture) AS arrivalTime,
// TIMEDIFF(sc.endDeparture, sc.startDeparture) AS journeyDuration,
// ro.routeName
// FROM schedules sc
// JOIN trains tr ON sc.trainId = tr.trainId
// JOIN routes ro ON ro.routeId = sc.routeId
// JOIN stations sd ON sd.stationId = sc.stationDepartureId
// JOIN stations sa ON sa.stationId = sc.stationArrivalId
// WHERE sc.stationDepartureId = 1
// AND sc.stationArrivalId = 2
// AND DATE(sc.startDeparture) = "2024-11-20"
// AND sc.statusSchedule = 1
// ORDER BY sc.startDeparture