package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.CarriageType;
import com.example.bookingtrain.service.CarriageTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carriageTypes")
public class CarriageTypeController {

    @Autowired
    private CarriageTypeService CarriageTypeService;

    @GetMapping
    public String getAllCarriageTypes(Model model) {
        List<CarriageType> carriageTypes = CarriageTypeService.getAllCarriageType();
        model.addAttribute("carriageTypes", carriageTypes);
        return "list/carriageTypeList";
    }

    @GetMapping("/newCarriageType")
    public String addCarriageTypeForm(Model model) {
        model.addAttribute("carriageTypes", new CarriageType());
        return "add/addCarriageType";
    }

    @PostMapping("/addCarriageType")
    public String addCarriageType(@ModelAttribute CarriageType CarriageType) {
        CarriageTypeService.createCarriageType(CarriageType);
        return "redirect:/carriageTypes";
    }

    // @GetMapping("/editCarriageType/{CarriageTypeID}")
    // public String editCarriageTypeForm(@PathVariable Long CarriageTypeID, Model
    // model)
    // {
    // CarriageType CarriageType =
    // CarriageTypeService.getCarriageTypeById(CarriageTypeID);
    // model.addAttribute("CarriageType", CarriageType);
    // return "edit/CarriageTypeEdit";
    // }

    // @PostMapping("/edit")
    // public String editCarriageType(@ModelAttribute CarriageType CarriageType) {
    // CarriageTypeService.updateCarriageType(CarriageType);
    // return "redirect:/CarriageTypes";
    // }

    // @GetMapping("/deleteCarriageType/{id}")
    // public String deleteCarriageType(@PathVariable Long id) {
    // CarriageTypeService.deleteCarriageType(id);
    // return "redirect:/CarriageTypes";
    // }

}
