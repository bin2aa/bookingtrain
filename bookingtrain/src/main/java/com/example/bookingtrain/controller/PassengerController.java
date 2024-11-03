package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("")
    public String showList(Model model) {
        List<Passenger> passengerList = passengerService.getAll();
        model.addAttribute("passengerList", passengerList);
        return "list/passengerList";
    }

    @GetMapping("/addPassenger")
    public String showAddPage(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "add/addPassenger";
    }

    @PostMapping("/savePassenger")
    public String savePassenger(Passenger passenger) {
        passengerService.save(passenger);
        return "redirect:/passengers";
    }


}
