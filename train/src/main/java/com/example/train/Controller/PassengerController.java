package com.example.train.Controller;

import com.example.train.DTO.SummaryDTO;
import com.example.train.Model.StationModel;
import com.example.train.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/addPassenger")
    public String showSearchForm(@RequestParam("trainCode") String trainCode,
                                 @RequestParam("stationDeparture") String departureStation,
                                 @RequestParam("stationArrival") String arrivalStation,
                                 @RequestParam("timeDeparture") String departureTime,
                                 @RequestParam("timeArrival") String arrivalTime,
                                 @RequestParam("personKid") int numberOfChildren,
                                 @RequestParam("personAdult") int numberOfAdult,
                                 Model model) {

        System.out.println("Train Code: " + trainCode);
        System.out.println("Departure Station: " + departureStation);
        System.out.println("Arrival Station: " + arrivalStation);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Adults: " + numberOfAdult);
        System.out.println("Kids: " + numberOfChildren);


        List<SummaryDTO> summary = ticketService.findSummaryInfo(trainCode, departureStation, arrivalStation, departureTime, arrivalTime, numberOfAdult, numberOfChildren);

        int totalPassengers = numberOfChildren + numberOfAdult;
        model.addAttribute("totalPassengers", totalPassengers);
        model.addAttribute("summarys", summary);
        return "Client/Components/Passenger";
    }
}
