package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Seat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Page<Seat> findAll(Pageable pageable);
}
