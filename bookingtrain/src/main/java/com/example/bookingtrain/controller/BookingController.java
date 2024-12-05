package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.*;
import com.example.bookingtrain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;
    private UserService userService;
    private EmployeeService employeeService;
    private ScheduleService scheduleService;
    private TicketService ticketService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, EmployeeService employeeService, ScheduleService scheduleService, TicketService ticketService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.employeeService = employeeService;
        this.scheduleService = scheduleService;
        this.ticketService = ticketService;
    }

    @GetMapping("")
    public String showListPage(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String search,
                               Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("bookingId").ascending());
        Page<Booking> bookingPage;
        if (search != null && !search.isEmpty()) {
            bookingPage = bookingService.searchByPassengerName(search, pageable);
        } else {
            bookingPage = bookingService.getAll(pageable);
        }

        List<User> userList = userService.getAllUsers();
        List<Employee> employeeList = employeeService.getAllEmployees();
        List<Schedule> scheduleList = scheduleService.getAllSchedules();

        HashMap<Integer, Integer> numberOfPassenger = new HashMap<Integer, Integer>();
        for(Booking booking : bookingPage.getContent()) {
            List<Ticket> ticketList = ticketService.searchTicketByBookingId(booking.getBookingId());
            numberOfPassenger.put(booking.getBookingId(), ticketList.size());
        };
        model.addAttribute("bookingList", bookingPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookingPage.getTotalPages());
        model.addAttribute("search", search);
        model.addAttribute("baseUrl", "/bookings");
        model.addAttribute("booking", new Booking());
        model.addAttribute("userList", userList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("numberOfPassenger", numberOfPassenger);

        return "/list/bookingList";
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.save(booking);
        return "redirect:/bookings/";
    }

    @GetMapping("/bookingDetails/{id}")
    public String showBookingDetails(@PathVariable int id, Model model) {
        Booking booking = bookingService.getById(id);
        if (booking == null) {
            return "redirect:/bookings/";
        }
        List<Ticket> ticketList = ticketService.searchTicketByBookingId(id);

        model.addAttribute("booking", booking);
        model.addAttribute("ticketList", ticketList);
        return "/list/bookingDetails";
    }

    @GetMapping("/editBooking/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        Booking booking = bookingService.getById(id);
        if(booking != null) {
            List<User> userList = userService.getAllUsers();
            List<Employee> employeeList = employeeService.getAllEmployees();
            List<Schedule> scheduleList = scheduleService.getAllSchedules();

            model.addAttribute("booking", booking);
            model.addAttribute("userList", userList);
            model.addAttribute("employeeList", employeeList);
            model.addAttribute("scheduleList", scheduleList);
            return "edit/editBooking";
        }
        return "redirect:/bookings/";
    }

    @PostMapping("/updateBooking")
    public String updateBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.save(booking);
        return "redirect:/bookings/";
    }

    @DeleteMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable int id) {
        bookingService.delete(id);
        return "redirect:/bookings/";
    }

    @GetMapping("/client")
    public String showClientPage(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<Booking> bookingList = bookingService.getByUserId(user.getUserId());

        HashMap<Integer, Integer> numberOfPassenger = new HashMap<Integer, Integer>();
        for(Booking b : bookingList){
            List<Ticket> ticketList = ticketService.searchTicketByBookingId(b.getBookingId());
            int passengerCount = ticketList.size();
            numberOfPassenger.put(b.getBookingId(), passengerCount);
        }

        model.addAttribute("bookingList", bookingList);
        model.addAttribute("numberOfPassenger", numberOfPassenger);
        return "/Client/Components/Booking";
    }

    @GetMapping("/client/details/{id}")
    public String showClientDetails(@PathVariable int id, Model model) {
        List<Ticket> ticketList =ticketService.searchTicketByBookingId(id);
        model.addAttribute("ticketList", ticketList);

        return "/Client/Components/BookingDetails";
    }

    @GetMapping("/client/deleteTicket/{id}")
    public String deletetDetails(@PathVariable int id, Model model) {
        ticketService.delete(id);

        return "/Client/Components/BookingDetails";
    }
}
