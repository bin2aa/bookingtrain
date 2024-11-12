package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.SeatType;
import com.example.bookingtrain.service.SeatTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seatTypes")
public class SeatTypeController {

    @Autowired
    private SeatTypeService seatTypeService;

    @GetMapping("")
    public String showList(Model model) {
        List<SeatType> seatTypeList = seatTypeService.getAllSeatTypes();
        model.addAttribute("seatTypeList", seatTypeList);
        return "list/seatTypeList";
    }

    @GetMapping("/addSeatType")
    public String showAddPage(Model model) {
        model.addAttribute("seatType", new SeatType());
        return "add/addSeatType";
    }

    @PostMapping("/saveSeatType")
    public String addSeatType(@ModelAttribute SeatType seatType) {
        seatTypeService.createSeatType(seatType);
        return "redirect:/seatTypes";
    }

    @GetMapping("/editSeatType/{id}")
    public String showUpdatePage(@PathVariable int id, Model model) {
        SeatType seatType = seatTypeService.getSeatTypeById(id);
        model.addAttribute("seatType", seatType);
        return "edit/editSeatType";
    }

    @PostMapping("/updateSeatType")
    public String updateSeatType(@ModelAttribute SeatType seatType) {
        seatTypeService.updateSeatType(seatType);
        return "redirect:/seatTypes";
    }

    @GetMapping("/deleteSeatType/{id}")
    public String deleteSeatType(@PathVariable int id) {
        seatTypeService.deleteSeatType(id);
        return "redirect:/seatTypes";
    }
}