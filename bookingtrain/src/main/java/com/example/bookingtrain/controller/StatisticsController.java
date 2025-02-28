package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.RevenueStatistic;
import com.example.bookingtrain.DTO.StationArrivalStatistic;
import com.example.bookingtrain.DTO.TrainRunStatistic;
import com.example.bookingtrain.DTO.TicketStatisticsDTO;
import com.example.bookingtrain.DTO.RouteStatisticsDTO;
import com.example.bookingtrain.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("totalEmployees", statisticsService.getTotalEmployees());
        model.addAttribute("totalPassengers", statisticsService.getTotalPassengers());
        model.addAttribute("totalSchedules", statisticsService.getTotalSchedules());
        model.addAttribute("activeTickets", statisticsService.getActiveTickets());
        model.addAttribute("totalRevenue", statisticsService.getTotalRevenue());
        model.addAttribute("activeTrains", statisticsService.getActiveTrains());
        model.addAttribute("activeRoutes", statisticsService.getActiveRoutes());
        return "/Client/statistic";
    }

    @Autowired
    private StatisticService statisticsService;

    // @GetMapping("/train-runs-by-month")
    // public ResponseEntity<List<TrainRunStatistic>>
    // getTrainRunsByMonth(@RequestParam int month) {
    // List<TrainRunStatistic> statistics =
    // statisticsService.getTrainRunsByMonth(month);
    // return ResponseEntity.ok(statistics);
    // }

    // @GetMapping("/arrival-by-station")
    // public ResponseEntity<List<StationArrivalStatistic>>
    // getArrivalByStation(@RequestParam int month) {
    // List<StationArrivalStatistic> statistics =
    // statisticsService.getArrivalByStation(month);
    // return ResponseEntity.ok(statistics);
    // }

    // @GetMapping("/revenue-by-month")
    // public ResponseEntity<List<RevenueStatistic>> getRevenue() {
    // List<RevenueStatistic> statistics = statisticsService.getRevenue();
    // return ResponseEntity.ok(statistics);
    // }

    @GetMapping("/revenue-by-day-month-year")
    public ResponseEntity<List<RevenueStatistic>> getRevenueByDayMonthYear() {
        List<RevenueStatistic> statistics = statisticsService.getRevenueByDayMonthYear();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/train-runs-by-day-month-year")
    public ResponseEntity<List<TrainRunStatistic>> getTrainRunsByDayMonthYear() {
        List<TrainRunStatistic> statistics = statisticsService.getTrainRunsByDayMonthYear();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/arrival-by-station-day-month-year")
    public ResponseEntity<List<StationArrivalStatistic>> getArrivalByStationDayMonthYear() {
        List<StationArrivalStatistic> statistics = statisticsService.getArrivalByStationDayMonthYear();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/tickets-by-year-month")
    public ResponseEntity<List<TicketStatisticsDTO>> getTicketsByYearMonth() {
        List<TicketStatisticsDTO> statistics = statisticsService.getTicketsByYearMonth();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/routes-by-run-and-train")
    public ResponseEntity<List<RouteStatisticsDTO>> getRoutesByRunAndTrain() {
        List<RouteStatisticsDTO> statistics = statisticsService.getRoutesByRunAndTrain();
        return ResponseEntity.ok(statistics);
    }

}