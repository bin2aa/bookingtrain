package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "/list/TestPage";
    }

    @GetMapping("")
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTicket();
        model.addAttribute("ticketList", tickets);
        return "list/ticketList";
    }
    @GetMapping("/blank")
    public String blank(Model model) {
        return "blank";
    }

}
