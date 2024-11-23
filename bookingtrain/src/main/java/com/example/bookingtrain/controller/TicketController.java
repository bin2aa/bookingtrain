package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.service.PassengerService;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private PassengerService passengerService;
    private StationService stationService;

    @Autowired
    public TicketController(TicketService ticketService, PassengerService passengerService,
            StationService stationService) {
        this.ticketService = ticketService;
        this.passengerService = passengerService;
        this.stationService = stationService;
    }

    @GetMapping("")
    public String getAllTickets(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("ticketId").ascending());
        Page<Ticket> ticketPage;

        if (search != null && !search.isEmpty()) {
            ticketPage = ticketService.searchTicketsByPassengerName(search, pageable);
        } else {
            ticketPage = ticketService.getAllTickets(pageable);
        }

        model.addAttribute("ticketList", ticketPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ticketPage.getTotalPages());
        model.addAttribute("baseUrl", "/tickets");
        model.addAttribute("search", search);
        return "list/ticketList";
    }

    @GetMapping("/addTicket")
    public String blank(Model model) {
        List<Passenger> passengerList = passengerService.getAll();
        List<Station> stationList = stationService.getAll();
        model.addAttribute("passengerList", passengerList);
        model.addAttribute("stationList", stationList);
        model.addAttribute("ticket", new Ticket());
        return "add/addTicket";
    }

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
        if (ticket.getTicketId() == null) {
            ticketService.save(ticket);
        } else {
            Ticket existingTicket = ticketService.getByID(ticket.getTicketId());
            existingTicket.setPassengerId(ticket.getPassengerId());
            existingTicket.setBookingId(ticket.getBookingId());
            existingTicket.setSeatId(ticket.getSeatId());
            ticketService.save(ticket);
        }

        return "redirect:/tickets";
    }

    @GetMapping("/editTicket/{id}")
    public String updateTicket(@PathVariable("id") int id, Model model) {
        Ticket ticket = ticketService.getByID(id);
        model.addAttribute("ticket", ticket);
        return "edit/editTicket";
    }

    @GetMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable("id") int id) {
        ticketService.delete(id);
        return "redirect:/list/ticketList";
    }
}
