package com.example.train.Controller;

import com.example.train.DTO.SummaryDTO;
import com.example.train.Model.CarriageTypeModel;
import com.example.train.Model.StationModel;
import com.example.train.Model.TicketInfoModel;
import com.example.train.Service.CarriageTypeService;
import com.example.train.Service.StationService;
import com.example.train.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CarriageTypeService carriageTypeService;

    @Autowired
    private StationService stationService;

    @GetMapping("/searchTickets")
    public String searchTickets(@RequestParam("sd") Long sd,
                                @RequestParam("sa") Long sa,
                                @RequestParam("ad") int ad,
                                @RequestParam("ki") int ki,
                                Model model) {
        // Retrieve tickets and carriage types
        List<TicketInfoModel> tickets = ticketService.findAvailableSeatsByStations(sd, sa, ad, ki);
        List<CarriageTypeModel> carriageTypes = carriageTypeService.getAllCarriageTypes();

        List<StationModel> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        // Add to model
        model.addAttribute("carriageTypes", carriageTypes);
        if (tickets.isEmpty()) {
            model.addAttribute("message", "No tickets found for the given route.");
        } else {
            model.addAttribute("tickets", tickets);
        }

        // Return the Thymeleaf template
        return "Client/Components/Home";
    }
}
