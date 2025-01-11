package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Coache;
import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.CoacheService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'coachees', 1)")
    @GetMapping("")
    public String showListPage(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("coacheId").ascending());
        Page<Coache> coachePage = coacheService.getAllCoaches(pageable);

        model.addAttribute("coacheList", coachePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coachePage.getTotalPages());
        model.addAttribute("baseUrl", "/coachees");

        // Page<Coache> coacheList = coacheService.getAll();
        // model.addAttribute("coacheList", coacheList);
        return "list/coacheList";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'coachees', 2)")
    @GetMapping("/newCoache")
    public String showAddPage(Model model) {
        var trainList = trainService.getAllTrains();

        model.addAttribute("trainList", trainList);
        model.addAttribute("coache", new Coache());
        return "add/addCoache";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'coachees', 2)")
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

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'coachees', 4)")
    @GetMapping("/editCoache/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        Coache coache = coacheService.getById(id);
        var trainList = trainService.getAllTrains();

        model.addAttribute("trainList", trainList);
        model.addAttribute("coache", coache);
        return "edit/editCoache";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'coachees', 4)")
    @PostMapping("/updateCoache")
    public String updateCoache(@ModelAttribute("coache") Coache coache) {
        coacheService.update(coache);
        return "redirect:/coachees";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'coachees', 3)")
    @GetMapping("/deleteCoache/{id}")
    public String deleteCoache(@PathVariable int id) {
        coacheService.delete(id);
        return "redirect:/coachees";
    }
}
