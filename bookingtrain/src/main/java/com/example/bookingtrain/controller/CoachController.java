package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Coach;
import com.example.bookingtrain.service.CoachService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coachs")
public class CoachController {

    private CoachService coachService;
    private TrainService trainService;

    @Autowired
    public CoachController(CoachService coachService, TrainService trainService) {
        this.coachService = coachService;
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showListPage(Model model) {
        List<Coach> coacheList = coachService.getAll();
        model.addAttribute("coachList", coacheList);
        return "list/coachList";
    }

    @GetMapping("/newcoach")
    public String showAddPage(Model model) {
        var trainList = trainService.getAllTrains();

        model.addAttribute("trainList", trainList);
        model.addAttribute("coach", new Coach());
        return "add/addCoach";
    }

    @PostMapping("/saveCoach")
    public String saveCoach(@ModelAttribute("coach") Coach coach) {
        if (coach.getCoachId() == null) {
            // coacheService.setStatusCoache(1);
            coachService.save(coach);
            return "redirect:/coachs";
        } else {
            Coach existingCoach = coachService.getById(coach.getCoachId());
            existingCoach.setTrainId(coach.getTrainId());
            coachService.save(coach);
        }
        return "redirect:/coachs";
    }

    @GetMapping("/deleteCoach/{id}")
    public String deleteCoach(@PathVariable int id) {
        coachService.delete(id);
        return "redirect:/coachs";
    }
}
