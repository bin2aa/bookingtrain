package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.PaymentDTO;
import com.example.bookingtrain.model.Booking;
import com.example.bookingtrain.model.Employee;
import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.model.Schedule;
import com.example.bookingtrain.model.Ticket;
import com.example.bookingtrain.model.User;
import com.example.bookingtrain.response.ResponseObject;
import com.example.bookingtrain.service.BookingService;
import com.example.bookingtrain.service.EmailService;
import com.example.bookingtrain.service.EmployeeService;
import com.example.bookingtrain.service.PassengerService;
import com.example.bookingtrain.service.PaymentService;
import com.example.bookingtrain.service.TicketService;
import com.example.bookingtrain.service.ObjectService;
import com.example.bookingtrain.service.UserService;
import com.example.bookingtrain.utils.QRCodeGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final BookingService bookingService;
    private final EmployeeService employeeService;
    private final PassengerService passengerService;
    private final TicketService ticketService;
    private final ObjectMapper objectMapper;
    private final ObjectService objectService;
    private final PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    public PaymentController(BookingService bookingService,
            EmployeeService employeeService,
            PassengerService passengerService,
            TicketService ticketService,
            ObjectMapper objectMapper,
            ObjectService objectService,
            PaymentService paymentService) {
        this.bookingService = bookingService;
        this.employeeService = employeeService;
        this.passengerService = passengerService;
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
        this.objectService = objectService;
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public Map<String, Object> createPayment(Integer userId, Integer scheduleId, Double totalPrice,
            String passengerDataJson, HttpSession session) throws Exception {
        // Create booking
        Employee employee = employeeService.findByUserId(userId);
        Integer employeeId = employee != null ? employee.getEmployeeId() : null;
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setEmployeeId(employeeId);
        booking.setScheduleId(scheduleId);
        booking.setTotal(totalPrice);
        booking.setDateBooking(new Date());
        booking.setStatusBooking(1);
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

        // Generate QR code
        String qrContent = "Booking ID: " + savedBooking.getBookingId() + "\n" +
                "Total Price: " + totalPrice + "\n" +
                "Date: " + new Date();
        byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(qrContent, 200, 200);
        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeImage);

        // Send email with order information and QR code
        User user = userService.getUserById(userId);
        String emailContent = "Dear " + user.getUsername() + ",\n\n" +
                "Your booking has been successfully created. Here are the details:\n" +
                "Booking ID: " + savedBooking.getBookingId() + "\n" +
                "Total Price: " + totalPrice + "\n" +
                "Date: " + new Date() + "\n\n" +
                "Thank you for choosing our service.\n\n" +
                "Best regards,\n" +
                "Booking Train Team";

        emailService.sendEmailWithAttachment(user.getEmail(), "Booking Confirmation", emailContent, qrCodeImage,
                "booking_qr_code.png");

        // Create booking details for view
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("bookingId", booking.getBookingId());
        bookingDetails.put("dateBooking", booking.getDateBooking());
        Schedule schedule = savedBooking.getSchedule();
        if (schedule != null) {
            bookingDetails.put("departureStation", schedule.getStationDeparture().getStationName());
            bookingDetails.put("arrivalStation", schedule.getStationArrival().getStationName());
            bookingDetails.put("routeInfo", schedule.getRoute().getRouteName());
            bookingDetails.put("departureTime", schedule.getStartDeparture());
        }
        bookingDetails.put("totalPrice", booking.getTotal());
        bookingDetails.put("qrCodeBase64", qrCodeBase64);

        return bookingDetails;
    }

    @PostMapping("/session/saveSeatId")
    @ResponseBody
    public ResponseEntity<?> saveSeatId(@RequestParam Integer passengerIndex,
            @RequestParam Integer seatId,
            HttpSession session) {
        session.setAttribute("seatId_" + passengerIndex, seatId);
        return ResponseEntity.ok().build();
    }

    // ------------------ Phần VNPay ------------------

    @GetMapping("/vn-pay")
    public RedirectView pay(HttpServletRequest request) {
        PaymentDTO.VNPayResponse response = paymentService.createVnPayPayment(request);
        return new RedirectView(response.getPaymentUrl());
    }

    // @GetMapping("/vn-pay")
    // public ResponseObject<PaymentDTO.VNPayResponse> pay(HttpServletRequest
    // request) {
    // return new ResponseObject<>(HttpStatus.OK, "Success",
    // paymentService.createVnPayPayment(request));
    // }

    // @GetMapping("/vn-pay-callback")
    // public ModelAndView payCallbackHandler(HttpServletRequest request) {
    // String status = request.getParameter("vnp_ResponseCode");
    // if (status != null && status.equals("00")) {
    // return new ModelAndView("Client/Components/PaymentConfirmation");
    // } else {
    // ModelAndView modelAndView = new
    // ModelAndView("Client/Components/PaymentConfirmation");
    // modelAndView.addObject("status", status);
    // modelAndView.addObject("message", "Payment failed. Please try again.");
    // return modelAndView;
    // }
    // }

    // @GetMapping("/vn-pay-callback")
    // public ModelAndView payCallbackHandler(HttpServletRequest request,
    // HttpSession session,
    // RedirectAttributes redirectAttributes) {
    // String status = request.getParameter("vnp_ResponseCode");
    // if (status != null && status.equals("00")) {
    // // Lấy các tham số cần thiết từ session hoặc request
    // Double totalPrice = (Double) session.getAttribute("totalPrice");
    // String passengerDataJson = (String)
    // session.getAttribute("passengerDataJson");

    // // Gọi lại hàm createPayment
    // return new ModelAndView(createPayment(totalPrice, passengerDataJson, session,
    // redirectAttributes));
    // } else {
    // ModelAndView modelAndView = new
    // ModelAndView("Client/Components/PaymentConfirmation");
    // modelAndView.addObject("status", status);
    // modelAndView.addObject("message", "Payment failed. Please try again.");
    // return modelAndView;
    // }
    // }

    @PostMapping("/session/saveTotalPriceAndPassengerData")
    @ResponseBody
    public ResponseEntity<?> saveTotalPriceAndPassengerData(@RequestParam Double totalPrice,
            @RequestParam String passengerData, HttpSession session) {
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("passengerDataJson", passengerData);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vn-pay-callback")
    public ModelAndView payCallbackHandler(HttpServletRequest request, HttpSession session,
            RedirectAttributes redirectAttributes) {
        String status = request.getParameter("vnp_ResponseCode");
        ModelAndView modelAndView = new ModelAndView("Client/Components/PaymentConfirmation");
        try {
            if (status != null && status.equals("00")) {
                Double totalPrice = (Double) session.getAttribute("totalPrice");
                String passengerDataJson = (String) session.getAttribute("passengerDataJson");
                Integer userId = (Integer) session.getAttribute("userId");
                Integer scheduleId = (Integer) session.getAttribute("scheduleId");
                if (userId == null || scheduleId == null) {
                    throw new RuntimeException("Missing session data");
                }

                // Call createPayment method
                Map<String, Object> bookingDetails = createPayment(userId, scheduleId, totalPrice, passengerDataJson,
                        session);

                modelAndView.addObject("bookingDetails", bookingDetails);
                modelAndView.addObject("totalPrice", totalPrice);
                modelAndView.addObject("passengerDataJson", passengerDataJson);
            } else {
                modelAndView.addObject("status", status);
                modelAndView.addObject("message", "Payment failed. Please try again.");
            }
        } catch (Exception e) {
            modelAndView.addObject("status", "error");
            modelAndView.addObject("message", "An error occurred: " + e.getMessage());
        }
        return modelAndView;
    }

    // @GetMapping("/vn-pay-callback")
    // public ModelAndView payCallbackHandler(HttpServletRequest request,
    // HttpSession session,
    // RedirectAttributes redirectAttributes) {
    // String status = request.getParameter("vnp_ResponseCode");
    // if (status != null && status.equals("00")) {
    // // Lấy các tham số cần thiết từ session hoặc request
    // Double totalPrice = (Double) session.getAttribute("totalPrice");
    // String passengerDataJson = (String)
    // session.getAttribute("passengerDataJson");

    // // Gọi lại hàm createPayment
    // return new ModelAndView(createPayment(totalPrice, passengerDataJson, session,
    // redirectAttributes));
    // } else {
    // ModelAndView modelAndView = new
    // ModelAndView("Client/Components/PaymentConfirmation");
    // modelAndView.addObject("status", status);
    // modelAndView.addObject("message", "Payment failed. Please try again.");
    // return modelAndView;
    // }
    // }

    // @GetMapping("/vn-pay-callback")
    // public ResponseObject<PaymentDTO.VNPayResponse>
    // payCallbackHandler(HttpServletRequest request) {
    // String status = request.getParameter("vnp_ResponseCode");
    // if (status.equals("00")) {
    // PaymentDTO.VNPayResponse vnPayResponse = new PaymentDTO.VNPayResponse(
    // "00",
    // "Success",
    // "Client/Components/PaymentConfirmation");

    // return new ResponseObject<>(
    // HttpStatus.OK,
    // "Success",
    // vnPayResponse);
    // } else {
    // return new ResponseObject<>(
    // HttpStatus.BAD_REQUEST,
    // "Failed",
    // null);
    // }
    // }

}