package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.InforReservationDTO;
import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.service.InfoReservationService;
import com.example.bookingtrain.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InfoRevervationController {
    @Autowired
    private InfoReservationService reservationService;
    @Autowired
    private StationService stationService;
    @GetMapping("/searchTickets")
    public String searchTickets(@RequestParam("sd") Long sd,
                                @RequestParam("sa") Long sa,
                                @RequestParam("da") String da,
                                Model model) {
        List<InforReservationDTO> tickets = reservationService.getReservations(sd, sa, da);
        List<Station> stations = stationService.getAll();
        model.addAttribute("stations", stations);
        if (tickets.isEmpty()) {
            model.addAttribute("message", "No tickets found for the given route.");
        } else {
            model.addAttribute("tickets", tickets);
        }

        return "Client/Components/Ticket";
    }
}
