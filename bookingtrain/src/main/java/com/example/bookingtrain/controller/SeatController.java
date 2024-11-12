package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Seat;
import com.example.bookingtrain.service.CoacheService;
import com.example.bookingtrain.service.SeatService;
import com.example.bookingtrain.service.SeatTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatTypeService seatTypeService;

    @Autowired
    private CoacheService coacheService;

    @GetMapping("")
    public String showList(Model model) {
        List<Seat> seatList = seatService.getAllSeats();
        model.addAttribute("seatList", seatList);
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
}