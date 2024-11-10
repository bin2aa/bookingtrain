package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String savePassenger(@ModelAttribute("passenger") Passenger passenger) {
        if(passenger.getPassengerId() == null){
            passengerService.save(passenger);
        }else {
            Passenger exsitingPassenger = passengerService.getById(passenger.getPassengerId());
            exsitingPassenger.setPassengerName(passenger.getPassengerName());
            exsitingPassenger.setDateOfBirth(passenger.getDateOfBirth());
            exsitingPassenger.setPhone(passenger.getPhone());
            exsitingPassenger.setIdentityId(passenger.getIdentityId());

            passengerService.save(passenger);
        }
        return "redirect:/passengers";
    }

    @GetMapping("/editPassenger/{id}")
    public String showUpdatePage(@PathVariable int id ,Model model) {
        Passenger p = passengerService.getById(id);
        model.addAttribute("passenger", p);
        return "edit/editPassenger";
    }

    @GetMapping("/deletePassenger/{id}")
    public String deletePassenger(@PathVariable int id) {
        passengerService.delete(id);
        return "redirect:/passengers";
    }

}
