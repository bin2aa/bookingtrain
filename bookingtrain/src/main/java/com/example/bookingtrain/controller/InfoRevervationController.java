package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.InforReservationDTO;
import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.service.InfoReservationService;
import com.example.bookingtrain.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String searchTickets(
            @RequestParam("sd") Long sd,
            @RequestParam("sa") Long sa,
            @RequestParam("da") String da,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 5; // Number of tickets per page
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<InforReservationDTO> ticketsPage = reservationService
                .getReservationsWithPagination(sd, sa, da, pageable);

        List<Station> stations = stationService.getAll();

        model.addAttribute("stations", stations);
        model.addAttribute("tickets", ticketsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ticketsPage.getTotalPages());
        model.addAttribute("baseUrl", "/searchTickets");

        // Add search parameters for pagination links
        model.addAttribute("sd", sd);
        model.addAttribute("sa", sa);
        model.addAttribute("da", da);

        return "Client/Components/Ticket";
    }
}