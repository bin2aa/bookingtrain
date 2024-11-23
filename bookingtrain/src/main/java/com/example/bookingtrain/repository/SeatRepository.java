package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Seat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Page<Seat> findAll(Pageable pageable);

    @Query("SELECT s.seatType.price FROM Seat s WHERE s.seatId = :seatId")
    Double findSeatPriceBySeatId(@Param("seatId") Integer seatId);
}
