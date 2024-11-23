package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Passenger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    Page<Passenger> findAll(Pageable pageable);

    Page<Passenger> findByPassengerNameContaining(String passengerName, Pageable pageable);

    @Query("SELECT o.price FROM Object o WHERE o.objectId = :objectId")
    Integer findObjectPriceByObjectId(@Param("objectId") Integer objectId);
}
