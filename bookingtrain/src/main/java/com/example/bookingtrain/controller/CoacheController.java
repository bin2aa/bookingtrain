package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Coache;
import com.example.bookingtrain.service.CoacheService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coachees")
public class CoacheController {

    private CoacheService coacheService;
    private TrainService trainService;

    @Autowired
    public CoacheController(CoacheService coacheService, TrainService trainService) {
        this.coacheService = coacheService;
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showListPage(Model model) {
        List<Coache> coacheList = coacheService.getAll();
        model.addAttribute("coacheList", coacheList);
        return "list/coacheList";
    }

    @GetMapping("/newCoache")
    public String showAddPage(Model model) {
        var trainList = trainService.getAllTrains();

        model.addAttribute("trainList", trainList);
        model.addAttribute("coache", new Coache());
        return "add/addCoache";
    }

    @PostMapping("/saveCoache")
    public String saveCoache(@ModelAttribute("coache") Coache coache) {
        if (coache.getCoacheId() == null) {
            // coacheService.setStatusCoache(1);
            coacheService.save(coache);
            return "redirect:/coachees";
        } else {
            Coache existingCoache = coacheService.getById(coache.getCoacheId());
            existingCoache.setTrainId(coache.getTrainId());
            coacheService.save(coache);
        }
        return "redirect:/coachees";
    }

    @GetMapping("/editCoache/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        Coache coache = coacheService.getById(id);
        var trainList = trainService.getAllTrains();

        model.addAttribute("trainList", trainList);
        model.addAttribute("coache", coache);
        return "edit/editCoache";
    }

    @PostMapping("/updateCoache")
    public String updateCoache(@ModelAttribute("coache") Coache coache) {
        coacheService.update(coache);
        return "redirect:/coachees";
    }

    @GetMapping("/deleteCoache/{id}")
    public String deleteCoache(@PathVariable int id) {
        coacheService.delete(id);
        return "redirect:/coachees";
    }
}
