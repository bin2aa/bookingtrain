package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Schedule;
import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.RouteService;
import com.example.bookingtrain.service.ScheduleService;
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
@RequestMapping("/schedules")
public class ScheduleController {

    private ScheduleService scheduleService;
    private StationService stationService;
    private TrainService trainService;
    private RouteService routeService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, StationService stationService,
            TrainService trainService, RouteService routeService) {
        this.scheduleService = scheduleService;
        this.stationService = stationService;
        this.trainService = trainService;
        this.routeService = routeService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("scheduleId").ascending());
        Page<Schedule> schedulePage;

        if (search != null && !search.isEmpty()) {
            schedulePage = scheduleService.searchSchedulesByTrainName(search, pageable);
        } else {
            schedulePage = scheduleService.getAllSchedules(pageable);
        }

        model.addAttribute("scheduleList", schedulePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", schedulePage.getTotalPages());
        model.addAttribute("baseUrl", "/schedules");
        model.addAttribute("search", search);
        return "list/scheduleList";
    }

    @GetMapping("/newSchedule")
    public String showAddPage(Model model) {
        var departureList = stationService.getAll();
        var arrivalList = stationService.getAll();
        var trainList = trainService.getAllTrains();
        var routeList = routeService.getAllRoutes();
        var stationList = stationService.getAll();

        model.addAttribute("departureList", departureList);
        model.addAttribute("arrivalList", arrivalList);
        model.addAttribute("trainList", trainList);
        model.addAttribute("routeList", routeList);
        model.addAttribute("stationList", stationList);
        model.addAttribute("schedule", new Schedule());
        return "add/addSchedule";
    }

    @PostMapping("/saveSchedule")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule, Model model) {
        if (schedule.getStationDepartureId().equals(schedule.getStationArrivalId())) {
            var departureList = stationService.getAll();
            var arrivalList = stationService.getAll();
            var trainList = trainService.getAllTrains();
            var routeList = routeService.getAllRoutes();
            var stationList = stationService.getAll();

            model.addAttribute("departureList", departureList);
            model.addAttribute("arrivalList", arrivalList);
            model.addAttribute("trainList", trainList);
            model.addAttribute("routeList", routeList);
            model.addAttribute("stationList", stationList);
            model.addAttribute("schedule", schedule);
            model.addAttribute("errorMessage", "Station Arrival and Station Departure cannot be the same.");
            return "add/addSchedule";
        }
        scheduleService.createSchedule(schedule);
        return "redirect:/schedules";
    }

    @PostMapping("/updateSchedule")
    public String updateSchedule(@ModelAttribute("schedule") Schedule schedule, Model model) {
        if (schedule.getStationDepartureId().equals(schedule.getStationArrivalId())) {
            var departureList = stationService.getAll();
            var arrivalList = stationService.getAll();
            var trainList = trainService.getAllTrains();
            var routeList = routeService.getAllRoutes();
            var stationList = stationService.getAll();

            model.addAttribute("departureList", departureList);
            model.addAttribute("arrivalList", arrivalList);
            model.addAttribute("trainList", trainList);
            model.addAttribute("routeList", routeList);
            model.addAttribute("stationList", stationList);
            model.addAttribute("schedule", schedule);
            model.addAttribute("errorMessage", "Station Arrival and Station Departure cannot be the same.");
            return "edit/editSchedule";
        }
        scheduleService.updateSchedule(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/editSchedule/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        var departureList = stationService.getAll();
        var arrivalList = stationService.getAll();
        var trainList = trainService.getAllTrains();
        var routeList = routeService.getAllRoutes();
        var stationList = stationService.getAll();

        model.addAttribute("departureList", departureList);
        model.addAttribute("arrivalList", arrivalList);
        model.addAttribute("trainList", trainList);
        model.addAttribute("routeList", routeList);
        model.addAttribute("stationList", stationList);
        model.addAttribute("schedule", schedule);
        return "edit/editSchedule";
    }

    @PostMapping("/editJson")
    @ResponseBody
    public String editScheduleStatus(@RequestBody Map<String, Integer> payload) {
        Integer scheduleId = payload.get("scheduleId");
        Integer statusSchedule = payload.get("statusSchedule");

        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        if (schedule == null) {
            return "error";
        }
        schedule.setStatusSchedule(statusSchedule);
        scheduleService.updateSchedule(schedule);
        return "success";
    }

    @GetMapping("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedules";
    }
}