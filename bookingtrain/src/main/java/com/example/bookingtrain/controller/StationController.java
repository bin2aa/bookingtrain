package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("")
    public String getAllStations(Model model) {
        List<Station> stations = stationService.getAll();
        model.addAttribute("stationList", stations);
        return "list/stationList";
    }

    @GetMapping("/addStation")
    public String addStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "add/addStation";
    }

    @PostMapping("/saveStation")
    public String saveStation(@ModelAttribute("station") Station station) {
        if(station.getStationId() != null) {
            stationService.save(station);
            Station existingStation = stationService.getById(station.getStationId());
            existingStation.setStationCode(station.getStationCode());
            existingStation.setStationName(station.getStationName());
            existingStation.setImage(station.getImage());
            existingStation.setDescription(station.getDescription());
            stationService.save(existingStation);
            return "redirect:/stations";
        }
        station.setStatusStation(1);
        stationService.save(station);
        return "redirect:/stations";
    }

    @GetMapping("/editStation/{id}")
    public String editStationForm(@PathVariable("id") int id, Model model) {
        Station station = stationService.getById(id);
        model.addAttribute("station", station);
        return "edit/editStation";
    }

    @GetMapping("/deleteStation/{id}")
    public String deleteStation(@PathVariable("id") int id) {
        stationService.deleteStation(id);
        return "redirect:/stations";
    }
}
