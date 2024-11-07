package com.example.train.Repository;

import com.example.train.DTO.SummaryDTO;
import com.example.train.Model.TicketInfoModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {
    private final JdbcTemplate jdbcTemplate;

    public TicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TicketInfoModel> findAvailableSeatsByStations(Long stationDepartureId, Long stationArrivalId,
            int numberOfAdults, int numberOfKids) {
        int numberOfPersons = numberOfAdults + numberOfKids; // Calculate total seats required

        String sql = "SELECT " +
                "    tr.trainCode, " +
                "    tr.trainName, " +
                "    sd.stationName AS departureStation, " +
                "    sa.stationName AS arrivalStation, " +
                "    MIN(de.timeToDeparture) AS departureTime, " +
                "    MAX(de.timeToArrival) AS arrivalTime, " +
                "    TIMEDIFF(MAX(de.timeToArrival), MIN(de.timeToDeparture)) AS totalTravelTime, " +
                "    ct.price AS price, " +
                "    DATE(MIN(de.timeToDeparture)) AS departureDate, " +
                "    CONCAT(HOUR(MIN(de.timeToDeparture)), ':', LPAD(MINUTE(MIN(de.timeToDeparture)), 2, '0')) AS departureTimeFormatted, "
                +
                "    DATE(MAX(de.timeToArrival)) AS arrivalDate, " +
                "    CONCAT(HOUR(MAX(de.timeToArrival)), ':', LPAD(MINUTE(MAX(de.timeToArrival)), 2, '0')) AS arrivalTimeFormatted "
                +
                "FROM " +
                "    detailroute de " +
                "JOIN routes ro ON de.routeId = ro.routeId " +
                "JOIN trains tr ON ro.trainId = tr.trainId " +
                "JOIN seats se ON de.seatId = se.seatId " +
                "JOIN carriages ca ON se.carriageId = ca.carriageId " +
                "JOIN carriagetype ct ON ct.carriageTypeId = ca.carriageTypeId " +
                "JOIN stations sd ON sd.stationId = ? " +
                "JOIN stations sa ON sa.stationId = ? " +
                "WHERE " +
                "    de.sequence >= ( " +
                "        SELECT MIN(det.sequence) " +
                "        FROM detailroute det " +
                "        WHERE det.stationDepartureId = ? " +
                "    ) " +
                "AND de.sequence <= ( " +
                "        SELECT MAX(del.sequence) " +
                "        FROM detailroute del " +
                "        WHERE del.stationArrivalId = ? " +
                "    ) " +
                "AND de.statusSeatId = 1 " + // Only include available seats
                "GROUP BY " +
                "    tr.trainCode, " +
                "    tr.trainName, " +
                "    sd.stationName, " +
                "    sa.stationName, " +
                "    ct.price " +
                "HAVING " +
                "    COUNT(de.statusSeatId) >= ?"; // Ensure enough available seats

        // Pass the numberOfPersons as the last parameter
        return jdbcTemplate.query(sql, new Object[] {
                stationDepartureId,
                stationArrivalId,
                stationDepartureId,
                stationArrivalId,
                numberOfPersons
        },
                (rs, rowNum) -> {
                    TicketInfoModel ticketInfo = new TicketInfoModel();
                    ticketInfo.setTrainCode(rs.getString("trainCode"));
                    ticketInfo.setTrainName(rs.getString("trainName"));
                    ticketInfo.setStationDeparture(rs.getString("departureStation"));
                    ticketInfo.setStationArrival(rs.getString("arrivalStation"));
                    ticketInfo.setTotalTravelTime(rs.getString("totalTravelTime"));
                    ticketInfo.setPrice(rs.getInt("price"));
                    ticketInfo.setDepartureDate(rs.getString("departureDate"));
                    ticketInfo.setArrivalDate(rs.getString("arrivalDate"));
                    ticketInfo.setTimeDepartureFormatted(rs.getString("departureTimeFormatted"));
                    ticketInfo.setTimeArrivalFormatted(rs.getString("arrivalTimeFormatted"));
                    ticketInfo.setTimeDeparture(rs.getString("departureTime"));
                    ticketInfo.setTimeArrival(rs.getString("arrivalTime"));
                    return ticketInfo;

                });
    }

    public List<SummaryDTO> findSummaryInfo(String trainCode, String stationDeparture, String stationArrival,
            String departureTime, String arrivalTime, int personAdult, int personKid) {
        String sql = "SELECT trains.trainName FROM trains WHERE trains.trainCode = ?";
        return jdbcTemplate.query(sql, new Object[] { trainCode }, (rs, rowNum) -> {
            SummaryDTO summary = new SummaryDTO();
            summary.setTrainName(rs.getString("trainName"));

            summary.setTrainCode(trainCode);
            summary.setDepartureStation(stationDeparture);
            summary.setArrivalStation(stationArrival);
            summary.setDepartureTime(departureTime);
            summary.setArrivalTime(arrivalTime);
            summary.setNumberOfAdults(personAdult);
            summary.setNumberOfChildren(personKid);
            return summary;
        });
    }

}
