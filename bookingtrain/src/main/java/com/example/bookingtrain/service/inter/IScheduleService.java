package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Schedule;

import java.util.List;

public interface IScheduleService {

    List<Schedule> getAll();
    Schedule getById(int id);
    Schedule save(Schedule schedule);
    boolean delete(int id);
}
