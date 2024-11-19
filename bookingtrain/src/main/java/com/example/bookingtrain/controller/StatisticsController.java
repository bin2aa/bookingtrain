package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.StationArrivalStatistic;
import com.example.bookingtrain.DTO.TrainRunStatistic;
import com.example.bookingtrain.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public String show() {
        return "/Client/statistic";
    }

    @Autowired
    private StatisticService statisticsService;

    @GetMapping("/train-runs-by-month")
    public ResponseEntity<List<TrainRunStatistic>> getTrainRunsByMonth(@RequestParam int month) {
        List<TrainRunStatistic> statistics = statisticsService.getTrainRunsByMonth(month);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/arrival-by-station")
    public ResponseEntity<List<StationArrivalStatistic>> getArrivalByStation(@RequestParam int month) {
        List<StationArrivalStatistic> statistics = statisticsService.getArrivalByStation(month);
        return ResponseEntity.ok(statistics);
    }

}