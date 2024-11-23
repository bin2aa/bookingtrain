package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Booking;
import com.example.bookingtrain.model.Employee;
import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.service.BookingService;
import com.example.bookingtrain.service.EmployeeService;
import com.example.bookingtrain.service.PassengerService;
import com.example.bookingtrain.service.TicketService;
import com.example.bookingtrain.service.ObjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final BookingService bookingService;
    private final EmployeeService employeeService;
    private final PassengerService passengerService;
    private final TicketService ticketService;
    private final ObjectMapper objectMapper;
    private final ObjectService objectService;

    @Autowired
    public PaymentController(BookingService bookingService,
            EmployeeService employeeService,
            PassengerService passengerService,
            TicketService ticketService,
            ObjectMapper objectMapper,
            ObjectService objectService) {
        this.bookingService = bookingService;
        this.employeeService = employeeService;
        this.passengerService = passengerService;
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
        this.objectService = objectService;
    }

    @PostMapping("/create")
    public String createPayment(@RequestParam Double totalPrice,
            @RequestParam("passengerData") String passengerDataJson,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            // Validate user login
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return "redirect:/login";
            }

            // Get schedule ID from session
            Integer scheduleId = (Integer) session.getAttribute("scheduleId");
            if (scheduleId == null) {
                redirectAttributes.addFlashAttribute("error", "Schedule ID not found");
                return "redirect:/home";
            }

            // Get employee ID if exists
            Employee employee = employeeService.findByUserId(userId);
            Integer employeeId = employee != null ? employee.getEmployeeId() : null;

            // Create booking
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setEmployeeId(employeeId);
            booking.setScheduleId(scheduleId);
            booking.setTotal(totalPrice);
            booking.setDateBooking(new Date());
            booking.setStatusBooking(1);

            // Save booking
            Booking savedBooking = bookingService.save(booking);

            // Parse passenger data
            List<Passenger> passengers = objectMapper.readValue(passengerDataJson,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Passenger.class));

            // Save passengers and create tickets
            for (int i = 0; i < passengers.size(); i++) {

                Passenger passenger = passengers.get(i);

                Passenger savedPassenger = passengerService.save(passenger);

                // Get seatId from session using passenger index
                Integer seatId = (Integer) session.getAttribute("seatId_" + (i + 1));

                Ticket ticket = new Ticket();
                ticket.setBookingId(savedBooking.getBookingId());
                ticket.setPassengerId(savedPassenger.getPassengerId());
                ticket.setSeatId(seatId);
                ticket.setIsActive(1);

                ticketService.save(ticket);
            }

            // Clear schedule and seatId from session
            session.removeAttribute("scheduleId");
            for (int i = 0; i < passengers.size(); i++) {
                session.removeAttribute("seatId_" + (i + 1));
            }

            // Add success message
            redirectAttributes.addFlashAttribute("success",
                    "Payment successful! Booking ID: " + savedBooking.getBookingId());

            return "redirect:/home";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Payment failed: " + e.getMessage());
            return "redirect:/home";
        }
    }

    @PostMapping("/session/saveSeatId")
    @ResponseBody
    public ResponseEntity<?> saveSeatId(@RequestParam Integer passengerIndex,
            @RequestParam Integer seatId,
            HttpSession session) {
        session.setAttribute("seatId_" + passengerIndex, seatId);
        return ResponseEntity.ok().build();
    }
}

// package com.example.bookingtrain.controller;

// import com.example.bookingtrain.model.Booking;
// import com.example.bookingtrain.model.Employee;
// import com.example.bookingtrain.model.Passenger;
// import com.example.bookingtrain.model.Ticket;
// import com.example.bookingtrain.service.BookingService;
// import com.example.bookingtrain.service.EmployeeService;
// import com.example.bookingtrain.service.PassengerService;
// import com.example.bookingtrain.service.TicketService;
// import com.example.bookingtrain.service.ObjectService;
// import com.example.bookingtrain.service.SeatService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import javax.servlet.http.HttpSession;
// import java.time.LocalDate;
// import java.time.Period;
// import java.util.Date;
// import java.util.List;

// @Controller
// @RequestMapping("/payment")
// public class PaymentController {

// private final BookingService bookingService;
// private final EmployeeService employeeService;
// private final PassengerService passengerService;
// private final TicketService ticketService;
// private final ObjectMapper objectMapper;
// private final ObjectService objectService;
// private final SeatService seatService;

// @Autowired
// public PaymentController(BookingService bookingService,
// EmployeeService employeeService,
// PassengerService passengerService,
// TicketService ticketService,
// ObjectMapper objectMapper,
// ObjectService objectService,
// SeatService seatService) {
// this.bookingService = bookingService;
// this.employeeService = employeeService;
// this.passengerService = passengerService;
// this.ticketService = ticketService;
// this.objectMapper = objectMapper;
// this.objectService = objectService;
// this.seatService = seatService;
// }

// @PostMapping("/create")
// public String createPayment(@RequestParam("passengerData") String
// passengerDataJson,
// HttpSession session,
// RedirectAttributes redirectAttributes) {
// try {
// // Validate user login
// Integer userId = (Integer) session.getAttribute("userId");
// if (userId == null) {
// return "redirect:/login";
// }

// // Get schedule ID from session
// Integer scheduleId = (Integer) session.getAttribute("scheduleId");
// if (scheduleId == null) {
// redirectAttributes.addFlashAttribute("error", "Schedule ID not found");
// return "redirect:/home";
// }

// // Get employee ID if exists
// Employee employee = employeeService.findByUserId(userId);
// Integer employeeId = employee != null ? employee.getEmployeeId() : null;

// // Parse passenger data
// List<Passenger> passengers = objectMapper.readValue(passengerDataJson,
// objectMapper.getTypeFactory().constructCollectionType(List.class,
// Passenger.class));

// double totalPrice = 0;

// // Calculate total price based on passengers
// for (int i = 0; i < passengers.size(); i++) {
// Passenger passenger = passengers.get(i);

// // Get seatId from session using passenger index
// Integer seatId = (Integer) session.getAttribute("seatId_" + (i + 1));
// double seatPrice = seatService.findSeatPriceBySeatId(seatId);

// // Determine object price based on age
// int age = Period.between(passenger.getDateOfBirth(),
// LocalDate.now()).getYears();
// int objectPrice = 0;
// if (age < 18) {
// objectPrice = objectService.getObjectById(1).getPrice(); // Giá của đối tượng
// Trẻ Em
// } else if (age >= 60) {
// objectPrice = objectService.getObjectById(3).getPrice(); // Giá của đối tượng
// Người Già
// } else {
// objectPrice = objectService.getObjectById(2).getPrice(); // Giá của đối tượng
// Người Lớn
// }

// double finalPrice = objectPrice + seatPrice;
// totalPrice += finalPrice;
// }

// // Create booking
// Booking booking = new Booking();
// booking.setUserId(userId);
// booking.setEmployeeId(employeeId);
// booking.setScheduleId(scheduleId);
// booking.setTotal(totalPrice);
// booking.setDateBooking(new Date());
// booking.setStatusBooking(1);

// // Save booking
// Booking savedBooking = bookingService.save(booking);

// // Save passengers and create tickets
// for (int i = 0; i < passengers.size(); i++) {
// Passenger passenger = passengers.get(i);
// Passenger savedPassenger = passengerService.save(passenger);

// // Get seatId from session using passenger index
// Integer seatId = (Integer) session.getAttribute("seatId_" + (i + 1));

// Ticket ticket = new Ticket();
// ticket.setBookingId(savedBooking.getBookingId());
// ticket.setPassengerId(savedPassenger.getPassengerId());
// ticket.setSeatId(seatId);
// ticket.setIsActive(1);

// ticketService.save(ticket);
// }

// // Clear schedule and seatId from session
// session.removeAttribute("scheduleId");
// for (int i = 0; i < passengers.size(); i++) {
// session.removeAttribute("seatId_" + (i + 1));
// }

// // Add success message
// redirectAttributes.addFlashAttribute("success",
// "Payment successful! Booking ID: " + savedBooking.getBookingId());

// return "redirect:/home";

// } catch (Exception e) {
// redirectAttributes.addFlashAttribute("error",
// "Payment failed: " + e.getMessage());
// return "redirect:/home";
// }
// }

// @PostMapping("/session/saveSeatId")
// @ResponseBody
// public ResponseEntity<?> saveSeatId(@RequestParam Integer passengerIndex,
// @RequestParam Integer seatId,
// HttpSession session) {
// session.setAttribute("seatId_" + passengerIndex, seatId);
// return ResponseEntity.ok().build();
// }
// }