package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private TicketRepository repo;
//    private Passenger

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.repo = ticketRepository;
    }

    // CRUD
    public Ticket getByID(int id) {
        return repo.findById((long) id).orElse(null);
    }

    public List<Ticket> getAllTicket() {
        return repo.findAll();
    }

    public Ticket create(Ticket ticket) {
        return repo.save(ticket);
    }

    public Ticket update(int id, Ticket ticket) {
        Ticket ticketTemp = getByID(id);
        if (ticketTemp != null) {
            ticketTemp.setPassenger(ticket.getPassenger());
            ticketTemp.setLegId(ticket.getLegId());
            ticketTemp.setStationDepartureId(ticket.getStationDepartureId());
            ticketTemp.setStationArrivalId(ticket.getStationArrivalId());
            ticketTemp.setTimeOfBooking(ticket.getTimeOfBooking());
        }
        return repo.save(ticket);
    }

    public void delete(int id) {
        repo.deleteById((long) id);
    }
}
