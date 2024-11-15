package com.example.bookingtrain.repository;

import com.example.bookingtrain.DTO.InforReservationDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InfoReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public InfoReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<InforReservationDTO> findAvailableSeatsByStations(Long stationDepartureId, Long stationArrivalId,
            String dateStart) {
        String sql = "SELECT " +
                "tr.trainId AS trainId, " +
                "sd.stationName AS Departure, " + // Ga khởi hành
                "sa.stationName AS Arrival, " + // Ga đến
                "tr.trainName AS trainCode, " + // Tên tàu
                "DATE(sc.startDeparture) AS DepartureDate, " + // Ngày khởi hành
                "TIME(sc.startDeparture) AS DepartureTime, " + // Thời gian khởi hành
                "DATE(sc.endDeparture) AS ArrivalDate, " + // Ngày đến
                "TIME(sc.endDeparture) AS ArrivalTime " + // Thời gian đến
                "FROM schedules sc " +
                "JOIN trains tr ON sc.trainId = tr.trainId " + // Kết nối với bảng trains
                "JOIN routes ro ON ro.trainId = tr.trainId " + // Kết nối với bảng routes
                "JOIN stations sd ON sd.stationId = ro.stationDepartureId " + // Kết nối với bảng stations (Ga khởi
                                                                              // hành)
                "JOIN stations sa ON sa.stationId = ro.stationArrivalId " + // Kết nối với bảng stations (Ga đến)
                "WHERE ro.stationDepartureId = ? " +
                "AND ro.stationArrivalId = ? " +
                "AND DATE(sc.startDeparture) = ?"; // So sánh ngày khởi hành

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            InforReservationDTO ticketInfo = new InforReservationDTO();
            ticketInfo.setTrainId(rs.getInt("trainId"));
            ticketInfo.setStationDeparture(rs.getString("Departure"));
            ticketInfo.setStationArrival(rs.getString("Arrival"));
            ticketInfo.setTrainCode(rs.getString("trainCode"));
            ticketInfo.setDateDepartureFormatted(rs.getString("DepartureDate"));
            ticketInfo.setTimeDepartureFormatted(rs.getString("DepartureTime"));
            ticketInfo.setDateArrivalFormatted(rs.getString("ArrivalDate"));
            ticketInfo.setTimeArrivalFormatted(rs.getString("ArrivalTime"));
            return ticketInfo;
        }, stationDepartureId, stationArrivalId, dateStart);
    }

}