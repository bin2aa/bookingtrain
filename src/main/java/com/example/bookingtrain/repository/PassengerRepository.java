package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
