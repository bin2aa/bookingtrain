package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Schedule;
import com.example.bookingtrain.service.ScheduleService;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    private ScheduleService scheduleService;
    private StationService stationService;
    private TrainService trainService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService
            , StationService stationService, TrainService trainService) {
        this.scheduleService = scheduleService;
        this.stationService = stationService;
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showList(Model model) {
        List<Schedule> scheduleList = scheduleService.getAll();
        model.addAttribute("scheduleList", scheduleList);
        return "list/scheduleList";
    }

    @GetMapping("/newSchedule")
    public String showAddPage(Model model) {
        var departureList = stationService.getAll();
        var arrivalList = stationService.getAll();
        var trainList = trainService.getAllTrains();

        model.addAttribute("departureList", departureList);
        model.addAttribute("arrivalList", arrivalList);
        model.addAttribute("trainList", trainList);

        model.addAttribute("schedule", new Schedule());
        return "add/addSchedule";
    }

    @PostMapping("/saveSchedule")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        if(schedule.getScheduleId() == null){
//            schedule.setStatusSchedule(1);
            scheduleService.save(schedule);
        }else{
            Schedule existingSchedule = scheduleService.getById(schedule.getScheduleId());
//            existingSchedule.setStationArrivalId(schedule.getStationArrivalId());
            existingSchedule.setRouteId(schedule.getRouteId());
            existingSchedule.setDepartureTime(schedule.getDepartureTime());
            existingSchedule.setArrivalTime(schedule.getArrivalTime());
            existingSchedule.setTrainId(schedule.getTrainId());
            scheduleService.save(existingSchedule);
        }
        return "redirect:/schedules";
    }

    @GetMapping("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable int id) {
        scheduleService.delete(id);
        return "redirect:/schedules";
    }
}
