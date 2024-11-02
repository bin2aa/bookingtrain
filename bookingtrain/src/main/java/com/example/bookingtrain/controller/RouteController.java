package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Route;
import com.example.bookingtrain.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

//    @GetMapping("")
//    public String showList(Model model) {
//        List<Route> routeList = routeService.getAllRoutes();
//        model.addAttribute("routeList", routeList);
//        return "/list/routes";
//    }
//
//    @GetMapping("/add")
//    public String showAddPage(Model model) {
//        model.addAttribute("route", new Route());
//        return "/add/route";
//    }
//
//    @PostMapping
//    public String saveRoute(@ModelAttribute("route") Route route) {
//        Route savedRoute = routeService.save(route);
//        if (savedRoute != null) {
//            return "redirect:/routes";
//        }
//        else{
//            return "redirect:/error";
//        }
//    }
//
//    @GetMapping("")
//    public String showuUpdatePage(Model model) {
//        model.addAttribute("route", new Route());
//        return "/update/route";
//    }

}
