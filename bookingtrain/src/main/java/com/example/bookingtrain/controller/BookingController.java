package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Booking;
import com.example.bookingtrain.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

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
}