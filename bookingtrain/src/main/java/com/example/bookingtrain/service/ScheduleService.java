package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Schedule;
import com.example.bookingtrain.repository.ScheduleRepository;
import com.example.bookingtrain.service.inter.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    private ScheduleRepository shceduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository shceduleRepository) {
        this.shceduleRepository = shceduleRepository;
    }

    public List<Schedule> getAll() {
        return shceduleRepository.findAll();
    }

    public Schedule getById(int id) {
        return shceduleRepository.findById(id).orElse(null);
    }

    public Schedule save(Schedule schedule) {
        return shceduleRepository.save(schedule);
    }

    public boolean delete(int id) {
        Schedule schedule = getById(id);
        if (schedule != null) {
            schedule.setStatusSchedule(0);
            shceduleRepository.save(schedule);
            return true;
        }
        return false;
    }

}
