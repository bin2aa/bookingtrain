package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Couch;
import com.example.bookingtrain.service.CouchService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/couches")
public class CouchController {

    private CouchService couchService;
    private TrainService trainService;

    @Autowired
    public CouchController(CouchService couchService, TrainService trainService) {
        this.couchService = couchService;
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showListPage(Model model) {
        List<Couch> couchList = couchService.getAll();
        model.addAttribute("couchList", couchList);
        return "list/couchList";
    }

    @GetMapping("/newCouch")
    public String showAddPage(Model model) {
        var trainList = trainService.getAllTrains();

        model.addAttribute("trainList", trainList);
        model.addAttribute("couch", new Couch());
        return "add/addCouch";
    }

    @PostMapping("/saveCouch")
    public String saveCouch(@ModelAttribute("couch") Couch couch) {
        if(couch.getCouchId() == null){
            couch.setStatusCouch(1);
            couchService.save(couch);
            return "redirect:/couches";
        }else {
            Couch existingCouch = couchService.getById(couch.getCouchId());
            existingCouch.setTrainId(couch.getTrainId());
            couchService.save(couch);
        }
        return "redirect:/couches";
    }

    @GetMapping("/deleteCouch/{id}")
    public String deleteCouch(@PathVariable int id) {
        couchService.delete(id);
        return "redirect:/couches";
    }
}
