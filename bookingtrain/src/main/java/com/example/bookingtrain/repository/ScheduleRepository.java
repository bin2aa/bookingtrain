package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Schedule;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Page<Schedule> findAll(Pageable pageable);

    @Query("SELECT s FROM Schedule s WHERE s.train.trainName LIKE %:trainName%")
    Page<Schedule> findByTrainNameContaining(String trainName, Pageable pageable);

    default int countPassengersByScheduleId(int scheduleId, JdbcTemplate jdbcTemplate) {
        String sql = "SELECT COUNT(DISTINCT t.passengerId) AS total_passengers " +
                "FROM schedules s " +
                "LEFT JOIN bookings b ON s.scheduleId = b.scheduleId " +
                "LEFT JOIN tickets t ON b.bookingId = t.bookingId " +
                "WHERE s.scheduleId = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, scheduleId);
    }

    @Query("SELECT p.passengerName FROM Schedule s " +
            "LEFT JOIN Booking b ON s.scheduleId = b.schedule.scheduleId " +
            "LEFT JOIN Ticket t ON b.bookingId = t.booking.bookingId " +
            "LEFT JOIN Passenger p ON t.passenger.passengerId = p.passengerId " +
            "WHERE s.scheduleId = :scheduleId")
    List<String> findPassengerNamesByScheduleId(@Param("scheduleId") int scheduleId);

    @Query("SELECT s FROM Schedule s WHERE s.statusSchedule = :status")
    Page<Schedule> findByStatus(@Param("status") int status, Pageable pageable);
}
