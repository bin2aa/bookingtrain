package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.SeatType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {

    Page<SeatType> findAll(Pageable pageable);

}
