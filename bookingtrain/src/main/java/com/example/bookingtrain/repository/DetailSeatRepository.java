package com.example.bookingtrain.repository;

import com.example.bookingtrain.DTO.DetailSeatDTO;
import com.example.bookingtrain.model.Seat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSeatRepository extends JpaRepository<Seat, Integer> {

    // @Query("SELECT new com.example.bookingtrain.DTO.DetailSeatDTO(" +
    // "s.seatId, s.seatNumber, co.coacheName, st.seatTypeName, st.price) " +
    // "FROM Seat s " +
    // "JOIN s.seatType st " +
    // "JOIN s.coache co " +
    // "WHERE co.train.trainId = :trainId")
    // List<DetailSeatDTO> findSeatsByTrainId(@Param("trainId") int trainId);
    @Query("SELECT new com.example.bookingtrain.DTO.DetailSeatDTO(" +
            "s.seatId, s.seatNumber, c.coacheName, st.seatTypeName, st.price) " +
            "FROM Seat s " +
            "JOIN s.coache c " +
            "JOIN s.seatType st " +
            "JOIN c.train t " +
            "WHERE t.trainId = :trainId " +
            "AND s.seatId NOT IN (" +
            "SELECT DISTINCT tk.seatId FROM Ticket tk " +
            "JOIN tk.booking b " +
            "WHERE b.scheduleId = :scheduleId " +
            "AND tk.isActive = 1" +
            ")")
    List<DetailSeatDTO> findAvailableSeatsByTrainAndSchedule(
            @Param("trainId") int trainId,
            @Param("scheduleId") int scheduleId);
}