package com.example.bookingtrain.service;

import com.example.bookingtrain.DTO.RevenueStatistic;
import com.example.bookingtrain.DTO.RouteStatisticsDTO;
import com.example.bookingtrain.DTO.StationArrivalStatistic;
import com.example.bookingtrain.DTO.TicketStatisticsDTO;
import com.example.bookingtrain.DTO.TrainRunStatistic;
import com.example.bookingtrain.model.Schedule;
import com.example.bookingtrain.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {
    // Thống kê số lượng chuyến tàu chạy theo tháng
    @Autowired
    private StatisticRepository statisticRepository;

    // Thống kê số lượng chuyến tàu chạy theo tháng
    public List<TrainRunStatistic> getTrainRunsByDayMonthYear() {
        return statisticRepository.countTrainRunsByDayMonthYear();
    }

    public List<StationArrivalStatistic> getArrivalByStationDayMonthYear() {
        return statisticRepository.countArrivalByStationDayMonthYear();
    }

    public List<RevenueStatistic> getRevenueByDayMonthYear() {
        return statisticRepository.revenueByDayMonthYear();
    }

    public List<TicketStatisticsDTO> getTicketsByYearMonth() {
        return statisticRepository.countTicketsByYearMonth();
    }

    public List<RouteStatisticsDTO> getRoutesByRunAndTrain() {
        return statisticRepository.countRoutesByRunAndTrain();
    }

    public Long getTotalEmployees() {
        return statisticRepository.countTotalEmployees();
    }

    public Long getTotalPassengers() {
        return statisticRepository.countTotalPassengers();
    }

    public Long getTotalSchedules() {
        return statisticRepository.countTotalSchedules();
    }

    public Long getActiveTickets() {
        return statisticRepository.countActiveTickets();
    }

    public Double getTotalRevenue() {
        return statisticRepository.getTotalRevenue();
    }

    public Long getActiveTrains() {
        return statisticRepository.countActiveTrains();
    }

    public Long getActiveRoutes() {
        return statisticRepository.countActiveRoutes();
    }

}