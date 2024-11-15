package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Schedule;
import com.example.bookingtrain.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Page<Schedule> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        Schedule existingSchedule = scheduleRepository.findById(schedule.getScheduleId()).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setTrainId(schedule.getTrainId());
            existingSchedule.setRouteId(schedule.getRouteId());
            existingSchedule.setStationDepartureId(schedule.getStationDepartureId());
            existingSchedule.setStationArrivalId(schedule.getStationArrivalId());
            existingSchedule.setStartDeparture(schedule.getStartDeparture());
            existingSchedule.setEndDeparture(schedule.getEndDeparture());
            existingSchedule.setStatusSchedule(schedule.getStatusSchedule());
            return scheduleRepository.saveAndFlush(existingSchedule);
        }
        return null;
    }

    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }
}