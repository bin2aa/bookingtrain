package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Route;
import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.service.RouteService;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;
    private StationService stationService;
    private TrainService trainService;

    @Autowired
    public RouteController(RouteService routeService, StationService stationService, TrainService trainService) {
        this.routeService = routeService;
        this.stationService = stationService;
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showList(Model model) {
        List<Route> routeList = routeService.getAllRoutes();
        model.addAttribute("routeList", routeList);
        return "/list/routeList";
    }

    @GetMapping("/addRoute")
    public String showAddPage(Model model) {
        model.addAttribute("route", new Route());
        var stationDeparturedList = stationService.getAll();
        var stationArrivingList = stationService.getAll();
        var trainList = trainService.getAllTrains();
        model.addAttribute("stationDepartedList", stationDeparturedList);
        model.addAttribute("stationArrivingList", stationArrivingList);
        model.addAttribute("trainList", trainList);
        return "/add/addRoute";
    }

    @PostMapping("/saveRoute")
    public String saveRoute(@ModelAttribute("route") Route route) {
        if(route.getRouteId() == null){
            routeService.save(route);
        }else{
            Route exsitingRoute = routeService.getById(route.getRouteId());
            exsitingRoute.setRouteName(route.getRouteName());
            exsitingRoute.setStationArrivalId(route.getStationArrivalId());
            exsitingRoute.setStationDepartureId(route.getStationDepartureId());
            exsitingRoute.setTrainId(route.getTrainId());
            routeService.save(exsitingRoute);
        }
        return "redirect:/routes";
    }

    @GetMapping("/editRoute/{id}")
    public String showuUpdatePage(@PathVariable("id") int routeID ,Model model) {
        Route route = routeService.getById(routeID);
        var stationDeparturedList = stationService.getAll();
        var stationArrivingList = stationService.getAll();
        var trainList = trainService.getAllTrains();
        model.addAttribute("route", route);
        model.addAttribute("stationDepartedList", stationDeparturedList);
        model.addAttribute("stationArrivingList", stationArrivingList);
        model.addAttribute("trainList", trainList);
        return "/edit/editRoute";
    }
}

// }
