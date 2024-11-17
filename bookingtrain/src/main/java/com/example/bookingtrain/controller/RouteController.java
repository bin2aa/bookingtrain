package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Route;
import com.example.bookingtrain.service.RouteService;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @GetMapping
    public String getAllRoutes(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 1;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("routeId").ascending());
        Page<Route> routePage = routeService.getAllRoutes(pageable); // Lấy danh sách routes từ service

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", routePage.getTotalPages());
        model.addAttribute("baseUrl", "/routes");
        // List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("routes", routePage);
        return "list/routeList";
    }

    @GetMapping("/newRoute")
    public String addRouteForm(Model model) {
        model.addAttribute("route", new Route());
        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("trains", trainService.getAllTrains());
        return "add/addRoute";
    }

    @PostMapping("/addRoute")
    public String addRoute(@ModelAttribute Route route) {
        routeService.createRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("/editRoute/{routeId}")
    public String editRouteForm(@PathVariable Integer routeId, Model model) {
        Route route = routeService.getRouteById(routeId);
        model.addAttribute("route", route);
        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("trains", trainService.getAllTrains());
        return "edit/editRoute";
    }

    @PostMapping("/edit")
    public String editRoute(@ModelAttribute Route route) {
        routeService.updateRoute(route);
        return "redirect:/routes";
    }

    @PostMapping("/editJson")
    @ResponseBody
    public String editRouteStatus(@RequestBody Map<String, Integer> payload) {
        Integer routeId = payload.get("routeId");
        Integer statusRoute = payload.get("statusRoute");

        Route route = routeService.getRouteById(routeId);
        if (route == null) {
            return "error";
        }
        route.setStatusRoute(statusRoute);
        routeService.updateRoute(route);
        return "success";
    }

    @GetMapping("/deleteRoute/{id}")
    public String deleteRoute(@PathVariable Integer id) {
        routeService.deleteRoute(id);
        return "redirect:/routes";
    }
}