package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.DetailSeatDTO;
import com.example.bookingtrain.model.Seat;
import com.example.bookingtrain.service.CoacheService;
import com.example.bookingtrain.service.DetailSeatService;
import com.example.bookingtrain.service.SeatService;
import com.example.bookingtrain.service.SeatTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatTypeService seatTypeService;

    @Autowired
    private CoacheService coacheService;

    @Autowired
    private DetailSeatService seatServiceDTO;

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page, Model model) {
        // List<Seat> seatList = seatService.getAllSeats();
        int pageSize = 1;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("seatId").ascending());
        Page<Seat> seatPage = seatService.getAllSeats(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", seatPage.getTotalPages());
        model.addAttribute("baseUrl", "/seats");
        model.addAttribute("seatList", seatPage);
        // model.addAttribute("seatList", seatList);
        return "list/seatList";
    }

    @GetMapping("/addSeat")
    public String showAddPage(Model model) {
        model.addAttribute("seat", new Seat());
        model.addAttribute("seatTypeList", seatTypeService.getAllSeatTypes());
        model.addAttribute("coacheList", coacheService.getAll());
        return "add/addSeat";
    }

    @PostMapping("/saveSeat")
    public String addSeat(@ModelAttribute Seat seat) {
        seatService.createSeat(seat);
        return "redirect:/seats";
    }

    @GetMapping("/editSeat/{id}")
    public String showUpdatePage(@PathVariable int id, Model model) {
        Seat seat = seatService.getSeatById(id);
        model.addAttribute("seat", seat);
        model.addAttribute("seatTypeList", seatTypeService.getAllSeatTypes());
        model.addAttribute("coacheList", coacheService.getAll());
        return "edit/editSeat";
    }

    @PostMapping("/updateSeat")
    public String updateSeat(@ModelAttribute Seat seat) {
        seatService.updateSeat(seat);
        return "redirect:/seats";
    }

    @GetMapping("/deleteSeat/{id}")
    public String deleteSeat(@PathVariable int id) {
        seatService.deleteSeat(id);
        return "redirect:/seats";
    }

    @GetMapping("/all")
    public ResponseEntity<List<DetailSeatDTO>> getAllSeats(@RequestParam int trainId, HttpSession session) {
        try {
            List<DetailSeatDTO> seats = seatServiceDTO.findSeatsByTrainId(trainId);
            session.setAttribute("seats", seats);
            return ResponseEntity.ok(seats); // Return JSON with 200 OK
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Return error response if an exception occurs
        }
    }

}