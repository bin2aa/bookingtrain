package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.*;
import com.example.bookingtrain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;
    private UserService userService;
    private ScheduleService scheduleService;
    private PassengerService passengerService;
    private EmployeeService employeeService;

//    @Autowired
//    public BookingController(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }

    @GetMapping("")
    public String showListPage(Model model) {
        List<Booking> bookingList = bookingService.getAll();
        model.addAttribute("bookingList", bookingList);

        return "list/bookingList";
    }

    @GetMapping("/addBooking")
    public String showAddPage(Model model) {
        Booking booking = new Booking();
        List<Passenger> passengerList = passengerService.getAll();
        List<User> userList = userService.getAllUsers();
        List<Employee> employeeList = employeeService.getAllEmployees();
        List<Schedule> scheduleList = scheduleService.getAll();

        model.addAttribute("booking", booking);
        model.addAttribute("userList",userList);
        model.addAttribute("passengerList",passengerList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("scheduleList",scheduleList);
        return "add/addBooking";
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        if(booking.getBookingId() == null){
            bookingService.save(booking);
            return "redirect:/bookings/";
        }else{
            Booking existingBooking = bookingService.getById(booking.getBookingId());
            existingBooking.setUserId(booking.getUserId());
            existingBooking.setScheduleId(booking.getScheduleId());
            existingBooking.setEmployeeId(booking.getEmployeeId());
            existingBooking.setDateBooking(booking.getDateBooking());
            existingBooking.setTotal(booking.getTotal());
            bookingService.save(existingBooking);
            return "redirect:/bookings/";
        }
    }

    @GetMapping("/editBooking/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        Booking booking = bookingService.getById(id);
        model.addAttribute("booking", booking);
        List<Passenger> passengerList = passengerService.getAll();
        List<User> userList = userService.getAllUsers();
        List<Employee> employeeList = employeeService.getAllEmployees();
        List<Schedule> scheduleList = scheduleService.getAll();

        model.addAttribute("booking", booking);
        model.addAttribute("userList",userList);
        model.addAttribute("passengerList",passengerList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("scheduleList",scheduleList);
        return "edit/editBooking";
    }

    @GetMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable int id) {
        bookingService.delete(id);
        return "redirect:/bookings/";
    }

}
