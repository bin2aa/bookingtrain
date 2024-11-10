// package com.example.bookingtrain.controller;

// import com.example.bookingtrain.model.Passenger;
// import com.example.bookingtrain.model.Station;
// import com.example.bookingtrain.model.Ticket;
// import com.example.bookingtrain.service.PassengerService;
// import com.example.bookingtrain.service.StationService;
// import com.example.bookingtrain.service.TicketService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @Controller
// @RequestMapping("/tickets")
// public class TicketController {

// private TicketService ticketService;
// private PassengerService passengerService;
// private StationService stationService;

// @Autowired
// public TicketController(TicketService ticketService, PassengerService
// passengerService, StationService stationService) {
// this.ticketService = ticketService;
// this.passengerService = passengerService;
// this.stationService = stationService;
// }

// @GetMapping("")
// public String getAllTickets(Model model) {
// List<Ticket> tickets = ticketService.getAllTicket();
// model.addAttribute("ticketList", tickets);
// return "list/ticketList";
// }

// @GetMapping("/addTicket")
// public String blank(Model model) {
// List<Passenger> passengerList = passengerService.getAll();
// List<Station> stationList = stationService.getAll();
// model.addAttribute("passengerList", passengerList);
// model.addAttribute("stationList", stationList);
// model.addAttribute("ticket", new Ticket());
// return "add/addTicket";
// }

// @PostMapping("/saveTicket")
// public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
// Passenger passenger = passengerService.getById(ticket.getPassengerId());
// Station stationDepartured =
// stationService.getById(ticket.getStationDepartureId());
// Station stationArrival =
// stationService.getById(ticket.getStationArrivalId());
// ticket.setPassenger(passenger);
// ticket.setStationDepartured(stationDepartured);
// ticket.setStationArrival(stationArrival);
// ticketService.save(ticket);
// return "redirect:/list/ticketList";
// }

// @GetMapping("/updateTicket/{id}")
// public String updateTicket(@PathVariable("id") int id, Model model) {
// Ticket ticket = ticketService.getByID(id);
// model.addAttribute("ticket", ticket);
// return "edit/editTicket";
// }

// @DeleteMapping("/deleteTicket/{id}")
// public String deleteTicket(@PathVariable("id") int id) {
// ticketService.delete(id);
// return "redirect:/list/ticketList";
// }
// }
