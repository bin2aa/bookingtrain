package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Booking;
import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.service.BookingService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'bookings', 1)")
    @GetMapping("")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAll();
        model.addAttribute("bookings", bookings);
        return "list/bookingList";
    }

    @GetMapping("/newBooking")
    public String addBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "add/addBooking";
    }

    @PostMapping("/addBooking")
    public String addBooking(@ModelAttribute Booking booking) {
        bookingService.save(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/editBooking/{bookingId}")
    public String editBookingForm(@PathVariable Integer bookingId, Model model) {
        Booking booking = bookingService.getById(bookingId);
        model.addAttribute("booking", booking);
        return "edit/editBooking";
    }

    @PostMapping("/editBooking")
    public String editBooking(@ModelAttribute Booking booking) {
        bookingService.save(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/deleteBooking/{bookingId}")
    public String deleteBooking(@PathVariable Integer bookingId) {
        bookingService.delete(bookingId);
        return "redirect:/bookings";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myBookings")
    public String getUserBookings(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        model.addAttribute("bookings", bookings);
        return "Client/Components/BookingDetail";
    }

    // In BookingController.java, add/uncomment this endpoint:
    @GetMapping("/details/{bookingId}")
    @ResponseBody
    public Map<String, Object> getBookingDetails(@PathVariable Integer bookingId) {
        Booking booking = bookingService.getById(bookingId);

        Map<String, Object> response = new HashMap<>();
        response.put("bookingId", booking.getBookingId());
        response.put("total", booking.getTotal());
        response.put("dateBooking", booking.getDateBooking());

        // Prepare station details
        Map<String, Object> scheduleDetails = new HashMap<>();
        Map<String, Object> departurStation = new HashMap<>();
        departurStation.put("statusStation", booking.getSchedule().getStationDeparture().getStatusStation());
        departurStation.put("description", booking.getSchedule().getStationDeparture().getDescription());

        Map<String, Object> arrivalStation = new HashMap<>();
        arrivalStation.put("statusStation", booking.getSchedule().getStationArrival().getStatusStation());
        arrivalStation.put("description", booking.getSchedule().getStationArrival().getDescription());

        scheduleDetails.put("stationDeparture", departurStation);
        scheduleDetails.put("stationArrival", arrivalStation);
        response.put("schedule", scheduleDetails);

        // Prepare ticket details with passenger info
        List<Map<String, Object>> ticketDetails = new ArrayList<>();
        for (Ticket ticket : booking.getTickets()) {
            Map<String, Object> ticketInfo = new HashMap<>();
            ticketInfo.put("ticketId", ticket.getTicketId());
            ticketInfo.put("seatId", ticket.getSeatId());

            // Passenger details
            if (ticket.getPassenger() != null) {
                Map<String, Object> passengerInfo = new HashMap<>();
                passengerInfo.put("passengerId", ticket.getPassenger().getPassengerId());
                passengerInfo.put("passengerName", ticket.getPassenger().getPassengerName());
                passengerInfo.put("phone", ticket.getPassenger().getPhone());
                passengerInfo.put("dateOfBirth", ticket.getPassenger().getDateOfBirth());

                ticketInfo.put("passenger", passengerInfo);
            }

            ticketDetails.add(ticketInfo);
        }
        response.put("tickets", ticketDetails);

        return response;
    }
}