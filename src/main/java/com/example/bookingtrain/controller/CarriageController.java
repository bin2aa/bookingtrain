package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Carriage;
import com.example.bookingtrain.service.CarriageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carriages")
public class CarriageController {

    @Autowired
    private CarriageService CarriageService;

    @GetMapping
    public String getAllCarriages(Model model) {
        List<Carriage> carriages = CarriageService.getAllCarriage();
        model.addAttribute("carriages", carriages);
        return "list/carriageList";
    }

    @GetMapping("/newCarriage")
    public String addCarriageForm(Model model) {
        model.addAttribute("Carriage", new Carriage());
        return "add/addCarriage";
    }

    @PostMapping("/addCarriage")
    public String addCarriage(@ModelAttribute Carriage Carriage) {
        CarriageService.createCarriage(Carriage);
        return "redirect:/carriages";
    }

    // @GetMapping("/editCarriage/{CarriageID}")
    // public String editCarriageForm(@PathVariable Long CarriageID, Model model)
    {
        // Carriage Carriage = CarriageService.getCarriageById(CarriageID);
        // model.addAttribute("Carriage", Carriage);
        // return "edit/CarriageEdit";
        // }

        // @PostMapping("/edit")
        // public String editCarriage(@ModelAttribute Carriage Carriage) {
        // CarriageService.updateCarriage(Carriage);
        // return "redirect:/Carriages";
        // }

        // @GetMapping("/deleteCarriage/{id}")
        // public String deleteCarriage(@PathVariable Long id) {
        // CarriageService.deleteCarriage(id);
        // return "redirect:/Carriages";
    }

}
