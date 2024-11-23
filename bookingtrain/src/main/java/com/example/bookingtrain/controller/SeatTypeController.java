package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.SeatType;
import com.example.bookingtrain.service.SeatTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public String showList(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("seatTypeId").ascending());
        Page<SeatType> seatTypePage = seatTypeService.getAllSeatTypes(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", seatTypePage.getTotalPages());
        model.addAttribute("baseUrl", "/seatTypes");
        model.addAttribute("seatTypeList", seatTypePage);
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