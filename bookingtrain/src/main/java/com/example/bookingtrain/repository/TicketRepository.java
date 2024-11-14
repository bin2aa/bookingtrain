package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
