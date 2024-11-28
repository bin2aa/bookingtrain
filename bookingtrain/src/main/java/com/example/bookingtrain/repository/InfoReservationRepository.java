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
                                "tr.trainId, " +
                                "sc.scheduleId, " +
                                "sd.stationName AS departureStation, " +
                                "sa.stationName AS arrivalStation, " +
                                "tr.trainName AS trainCode, " +
                                "tr.image AS trainImage, " +
                                "DATE(sc.startDeparture) AS departureDate, " +
                                "TIME(sc.startDeparture) AS departureTime, " +
                                "DATE(sc.endDeparture) AS arrivalDate, " +
                                "TIME(sc.endDeparture) AS arrivalTime, " +
                                "TIMEDIFF(sc.endDeparture, sc.startDeparture) AS journeyDuration, " +
                                "sdc.stationName AS scheduledDepartureStation, " +
                                "sac.stationName AS scheduledArrivalStation, " +
                                "(SELECT COUNT(*) FROM seats s " +
                                " JOIN coaches c ON s.coacheId = c.coacheId " +
                                " WHERE c.trainId = tr.trainId) AS totalSeats, " +
                                "(SELECT COUNT(*) FROM tickets t " +
                                " JOIN bookings b ON t.bookingId = b.bookingId " +
                                " WHERE b.scheduleId = sc.scheduleId) AS bookedSeats, " +
                                "ro.routeName " +
                                "FROM schedules sc " +
                                "JOIN trains tr ON sc.trainId = tr.trainId " +
                                "JOIN routes ro ON ro.routeId = sc.routeId " +
                                "JOIN stations sd ON sd.stationId = ro.stationDepartureId " +
                                "JOIN stations sa ON sa.stationId = ro.stationArrivalId " +
                                "JOIN stations sdc ON sdc.stationId = sc.stationDepartureId " +
                                "JOIN stations sac ON sac.stationId = sc.stationArrivalId " +
                                "WHERE ro.stationDepartureId = ? " +
                                "AND ro.stationArrivalId = ? " +
                                "AND DATE(sc.startDeparture) = ? " +
                                "AND sc.statusSchedule = 1 " +
                                "ORDER BY sc.startDeparture " +
                                "LIMIT ? OFFSET ?";

                String countSql = "SELECT COUNT(*) " +
                                "FROM schedules sc " +
                                "JOIN routes ro ON ro.routeId = sc.routeId " +
                                "WHERE ro.stationDepartureId = ? " +
                                "AND ro.stationArrivalId = ? " +
                                "AND DATE(sc.startDeparture) = ?";

                int total = jdbcTemplate.queryForObject(countSql, Integer.class,
                                stationDepartureId, stationArrivalId, dateStart);

                List<InforReservationDTO> tickets = jdbcTemplate.query(sql, (rs, rowNum) -> {
                        InforReservationDTO ticketInfo = new InforReservationDTO();
                        ticketInfo.setTrainId(rs.getInt("trainId"));
                        ticketInfo.setScheduleId(rs.getInt("scheduleId"));
                        ticketInfo.setStationDeparture(rs.getString("departureStation"));
                        ticketInfo.setStationArrival(rs.getString("arrivalStation"));
                        ticketInfo.setTrainCode(rs.getString("trainCode"));
                        ticketInfo.setImage(rs.getString("trainImage"));
                        ticketInfo.setDateDepartureFormatted(rs.getString("departureDate"));
                        ticketInfo.setTimeDepartureFormatted(rs.getString("departureTime"));
                        ticketInfo.setDateArrivalFormatted(rs.getString("arrivalDate"));
                        ticketInfo.setTimeArrivalFormatted(rs.getString("arrivalTime"));
                        ticketInfo.setScheduleDeparture(rs.getString("scheduledDepartureStation"));
                        ticketInfo.setScheduleArrival(rs.getString("scheduledArrivalStation"));
                        ticketInfo.setTotalTime(rs.getString("journeyDuration"));
                        ticketInfo.setRouteName(rs.getString("routeName")); // Set routeName
                        // ticketInfo.setDateDepartureFormatted(rs.getString("DepartureDate"));
                        // ticketInfo.setTimeDepartureFormatted(rs.getString("DepartureTime"));
                        // ticketInfo.setDateArrivalFormatted(rs.getString("ArrivalDate"));
                        // ticketInfo.setTimeArrivalFormatted(rs.getString("ArrivalTime"));
                        // ticketInfo.setScheduleDeparture(rs.getString("ScheduleDeparture"));
                        // ticketInfo.setScheduleArrival(rs.getString("ScheduleArrival"));
                        // ticketInfo.setTotalTime(rs.getString("TotalTime"));

                        // ticketInfo.setTotalSeats(rs.getInt("totalSeats"));
                        // ticketInfo.setBookedSeats(rs.getInt("bookedSeats"));
                        // ticketInfo.setRouteName(rs.getString("routeName"));
                        return ticketInfo;
                }, stationDepartureId, stationArrivalId, dateStart,
                                pageable.getPageSize(), pageable.getOffset());

                return new PageImpl<>(tickets, pageable, total);
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
// sdc.stationName AS scheduledDepartureStation,
// sac.stationName AS scheduledArrivalStation,
// (SELECT COUNT(*)
// FROM seats s
// JOIN coaches c ON s.coacheId = c.coacheId
// WHERE c.trainId = tr.trainId) AS totalSeats,
// (SELECT COUNT(*)
// FROM tickets t
// JOIN bookings b ON t.bookingId = b.bookingId
// WHERE b.scheduleId = sc.scheduleId) AS bookedSeats,
// ro.routeName
// FROM schedules sc
// JOIN trains tr ON sc.trainId = tr.trainId
// JOIN routes ro ON ro.routeId = sc.routeId
// JOIN stations sd ON sd.stationId = ro.stationDepartureId
// JOIN stations sa ON sa.stationId = ro.stationArrivalId
// JOIN stations sdc ON sdc.stationId = sc.stationDepartureId
// JOIN stations sac ON sac.stationId = sc.stationArrivalId
// WHERE
// sdc.stationName = 'GA HUẾ'
// AND sac.stationName = 'GA HÀ NỘI'
// AND DATE(sc.startDeparture) = '2024-11-27'
// AND sc.statusSchedule = 1
// ORDER BY sc.startDeparture;
