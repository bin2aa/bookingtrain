package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.TrainDetailsDTO;
import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.service.ObjectService;
import com.example.bookingtrain.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private PassengerService passengerService;

    private ObjectService objectService;

    @Autowired
    public PassengerController(PassengerService passengerService, ObjectService objectService) {
        this.passengerService = passengerService;
        this.objectService = objectService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("passengerId").ascending());
        Page<Passenger> passengerPage;

        if (search != null && !search.isEmpty()) {
            passengerPage = passengerService.searchPassengersByName(search, pageable);
        } else {
            passengerPage = passengerService.getAllPassengers(pageable);
        }

        model.addAttribute("passengerList", passengerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", passengerPage.getTotalPages());
        model.addAttribute("baseUrl", "/passengers");
        model.addAttribute("search", search);
        return "list/passengerList";
    }

    @GetMapping("/addPassenger")
    public String showAddPage(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "add/addPassenger";
    }

    @PostMapping("/savePassenger")
    public String savePassenger(@ModelAttribute("passenger") Passenger passenger) {
        if (passenger.getPassengerId() == null) {
            passengerService.save(passenger);
        } else {
            Passenger exsitingPassenger = passengerService.getById(passenger.getPassengerId());
            exsitingPassenger.setPassengerName(passenger.getPassengerName());
            exsitingPassenger.setDateOfBirth(passenger.getDateOfBirth());
            exsitingPassenger.setPhone(passenger.getPhone());
            exsitingPassenger.setIdentityId(passenger.getIdentityId());
            exsitingPassenger.setObject(passenger.getObject());

            passengerService.save(passenger);
        }
        return "redirect:/passengers";
    }

    @GetMapping("/editPassenger/{id}")
    public String showUpdatePage(@PathVariable int id, Model model) {
        Passenger p = passengerService.getById(id);
        model.addAttribute("passenger", p);
        model.addAttribute("objects", objectService.getAllObjects());
        return "edit/editPassenger";
    }

    @GetMapping("/deletePassenger/{id}")
    public String deletePassenger(@PathVariable int id) {
        passengerService.delete(id);
        return "redirect:/passengers";
    }

    @GetMapping("/ticket/passenger")
    public String passenger(TrainDetailsDTO trainDetails, Model model) {
        model.addAttribute("passenger", new Passenger());

        model.addAttribute("trainCode", trainDetails.getTrainCode());
        model.addAttribute("stationDeparture", trainDetails.getStationDeparture());
        model.addAttribute("stationArrival", trainDetails.getStationArrival());
        model.addAttribute("timeDeparture", trainDetails.getTimeDeparture());
        model.addAttribute("dateDeparture", trainDetails.getDateDeparture());
        model.addAttribute("timeArrival", trainDetails.getTimeArrival());
        model.addAttribute("dateArrival", trainDetails.getDateArrival());
        return "Client/Components/Passenger";
    }

}