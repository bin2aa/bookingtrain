package com.example.bookingtrain.service;
import com.example.bookingtrain.DTO.StationArrivalStatistic;
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
    public List<TrainRunStatistic> getTrainRunsByMonth(int month) {
        return statisticRepository.countTrainRunsByMonth(month);
    }

    // Thống kê số lượng lượt đến của các trạm
    public List<StationArrivalStatistic> getArrivalByStation(int month) {
        return statisticRepository.countArrivalByStation(month);
    }
}
