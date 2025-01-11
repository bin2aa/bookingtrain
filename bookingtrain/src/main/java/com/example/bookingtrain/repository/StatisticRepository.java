package com.example.bookingtrain.repository;

import com.example.bookingtrain.DTO.RevenueStatistic;
import com.example.bookingtrain.DTO.RouteStatisticsDTO;
import com.example.bookingtrain.DTO.StationArrivalStatistic;
import com.example.bookingtrain.DTO.TicketStatisticsDTO;
import com.example.bookingtrain.DTO.TrainRunStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.bookingtrain.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Schedule, Integer> {

    // Thống kê doanh thu theo ngày, tháng, năm
    @Query(value = """
                SELECT new com.example.bookingtrain.DTO.RevenueStatistic(
                    FUNCTION('DAY', b.dateBooking),
                    FUNCTION('MONTH', b.dateBooking),
                    FUNCTION('YEAR', b.dateBooking),
                    SUM(b.total)
                )
                FROM Booking b
                WHERE b.statusBooking = 1
                GROUP BY FUNCTION('YEAR', b.dateBooking), FUNCTION('MONTH', b.dateBooking), FUNCTION('DAY', b.dateBooking)
                ORDER BY FUNCTION('YEAR', b.dateBooking), FUNCTION('MONTH', b.dateBooking), FUNCTION('DAY', b.dateBooking)
            """)
    List<RevenueStatistic> revenueByDayMonthYear();

    // Thống kê số lượng chuyến tàu chạy theo tháng
    @Query(value = """
                SELECT new com.example.bookingtrain.DTO.TrainRunStatistic(
                    FUNCTION('DAY', s.startDeparture),
                    FUNCTION('MONTH', s.startDeparture),
                    FUNCTION('YEAR', s.startDeparture),
                    COUNT(*)
                )
                FROM Schedule s
                GROUP BY FUNCTION('YEAR', s.startDeparture), FUNCTION('MONTH', s.startDeparture), FUNCTION('DAY', s.startDeparture)
                ORDER BY FUNCTION('YEAR', s.startDeparture), FUNCTION('MONTH', s.startDeparture), FUNCTION('DAY', s.startDeparture)
            """)
    List<TrainRunStatistic> countTrainRunsByDayMonthYear();

    // Thống kê số lượng lượt đến của các trạm
    @Query(value = """
                SELECT new com.example.bookingtrain.DTO.StationArrivalStatistic(
                    s.stationArrival.stationName,
                    FUNCTION('DAY', s.startDeparture),
                    FUNCTION('MONTH', s.startDeparture),
                    FUNCTION('YEAR', s.startDeparture),
                    COUNT(*)
                )
                FROM Schedule s
                GROUP BY s.stationArrival.stationId, FUNCTION('YEAR', s.startDeparture), FUNCTION('MONTH', s.startDeparture), FUNCTION('DAY', s.startDeparture)
                ORDER BY s.stationArrival.stationId, FUNCTION('YEAR', s.startDeparture), FUNCTION('MONTH', s.startDeparture), FUNCTION('DAY', s.startDeparture)
            """)
    List<StationArrivalStatistic> countArrivalByStationDayMonthYear();

    // ===========================================

    // Thống kê số lượng vé và số lượng vé hoạt động theo năm và tháng
    @Query(value = """
            SELECT new com.example.bookingtrain.DTO.TicketStatisticsDTO(
                YEAR(b.dateBooking),
                MONTH(b.dateBooking),
                COUNT(t.ticketId),
                SUM(t.isActive)
            )
            FROM Booking b
            JOIN Ticket t ON b.bookingId = t.bookingId
            GROUP BY YEAR(b.dateBooking), MONTH(b.dateBooking)
            ORDER BY YEAR(b.dateBooking), MONTH(b.dateBooking)
            """)
    List<TicketStatisticsDTO> countTicketsByYearMonth();

    // Thống kê số lần chạy và số tàu chạy theo tuyến
    @Query(value = """
            SELECT new com.example.bookingtrain.DTO.RouteStatisticsDTO(
                r.routeName,
                COUNT(s.scheduleId),
                COUNT(DISTINCT t.trainId)
            )
            FROM Route r
            JOIN Schedule s ON r.routeId = s.routeId
            JOIN Train t ON r.trainId = t.trainId
            GROUP BY r.routeName
            ORDER BY COUNT(s.scheduleId) DESC
            """)
    List<RouteStatisticsDTO> countRoutesByRunAndTrain();

    @Query("SELECT COUNT(e) FROM Employee e")
    Long countTotalEmployees();

    // Get total number of passengers
    @Query("SELECT COUNT(p) FROM Passenger p")
    Long countTotalPassengers();

    // Get total number of schedules
    @Query("SELECT COUNT(s) FROM Schedule s")
    Long countTotalSchedules();

    // Get total number of active tickets
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.isActive = 1")
    Long countActiveTickets();

    // Get total revenue from all bookings
    @Query("SELECT COALESCE(SUM(b.total), 0) FROM Booking b")
    Double getTotalRevenue();

    // Get total number of active trains
    @Query("SELECT COUNT(t) FROM Train t WHERE t.statusTrain = 1")
    Long countActiveTrains();

    // Get total number of active routes
    @Query("SELECT COUNT(r) FROM Route r WHERE r.statusRoute = 1")
    Long countActiveRoutes();
}