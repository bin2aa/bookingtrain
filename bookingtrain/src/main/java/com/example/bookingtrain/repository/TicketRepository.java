package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Page<Ticket> findAll(Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE t.passenger.passengerName LIKE %:passengerName%")
    Page<Ticket> findByPassengerNameContaining(String passengerName, Pageable pageable);
}