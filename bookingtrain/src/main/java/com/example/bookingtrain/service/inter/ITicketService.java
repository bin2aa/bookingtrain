package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Ticket;

import java.util.List;

public interface ITicketService {
    // Bussiness Logic
    Ticket getByID(int id);
    List<Ticket> getAllTicket();
    Ticket save(Ticket ticket);
    Ticket update(int id, Ticket ticket);
    boolean delete(int id);
}
