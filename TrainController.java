package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trains")
public class TrainController {

    private TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showTrains(Model model) {
        List<Train> listTrain = trainService.getAll();
        model.addAttribute("listTrain", listTrain);
        return "list/trainList";
    }

    @GetMapping("/newTrain")
    public String showAddPage(Model model) {
        // Truyen model moi vao create form
        model.addAttribute("train", new Train());
        return "add/addTrain";
    }

    @PostMapping("/save")
    public String saveTrain(@ModelAttribute("train") Train train) {
        // Lay model co ten la train tu create form ra
        trainService.create(train);
        return "redirect:/trains";
    }

    @GetMapping("/edit/{trainID}")
    public String showEditPage(@PathVariable(name = "trainID") long trainID, Model model) {
        // Tim train bang duong dan
        Train train = trainService.getById(trainID);
        model.addAttribute("train", train);
        // Show edit page
        return "edit/trainEdit";
    }

    @GetMapping("/delete/{trainID}")
    public String deleteTrain(@PathVariable(name = "trainID") long trainID) {
        trainService.delete(trainID);
        return "redirect:/trains";
    }
}
