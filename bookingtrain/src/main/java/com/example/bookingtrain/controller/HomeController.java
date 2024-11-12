package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.service.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StationService stationService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String showStation(Model model) {
        List<Station> stations = stationService.getAll();
        model.addAttribute("stations", stations);
        return "/Client/Components/Home";
    }

}