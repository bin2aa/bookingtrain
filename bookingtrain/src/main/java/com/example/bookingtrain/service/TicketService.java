package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.repository.TicketRepository;
import com.example.bookingtrain.service.inter.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {

    private final TicketRepository repo;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.repo = ticketRepository;
    }

    public Ticket getByID(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Ticket> getAllTicket() {
        return repo.findAll();
    }

    public Page<Ticket> getAllTickets(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Ticket> searchTicketsByPassengerName(String passengerName, Pageable pageable) {
        return repo.findByPassengerNameContaining(passengerName, pageable);
    }

    public Ticket save(Ticket ticket) {
        return repo.save(ticket);
    }

    public Ticket update(int id, Ticket ticket) {
        Ticket ticketTemp = getByID(id);
        if (ticketTemp != null) {
            ticketTemp.setPassengerId(ticket.getPassengerId());
            ticketTemp.setSeatId(ticket.getSeatId());
            ticketTemp.setBookingId(ticket.getBookingId());
        }
        return repo.save(ticket);
    }

    public boolean delete(int id) {
        Ticket ticket = getByID(id);
        if (ticket != null) {
            ticket.setIsActive(0);
            repo.save(ticket);
            return true;
        }
        return false;
    }
}