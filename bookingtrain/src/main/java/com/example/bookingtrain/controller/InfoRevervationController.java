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
            @RequestParam("sd") Long sd, // Departure station ID
            @RequestParam("sa") Long sa, // Arrival station ID
            @RequestParam("da") String da, // Departure date
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<InforReservationDTO> ticketsPage = reservationService
                .getReservationsWithPagination(sd, sa, da, pageable); // Chú ý thứ tự tham số ở đây

        List<Station> stations = stationService.getAll();

        model.addAttribute("stations", stations);
        model.addAttribute("tickets", ticketsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ticketsPage.getTotalPages());
        model.addAttribute("baseUrl", "/searchTickets");

        model.addAttribute("sd", sd);
        model.addAttribute("sa", sa);
        model.addAttribute("da", da);

        return "Client/Components/Ticket";
    }

}