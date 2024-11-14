package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Coach;
import com.example.bookingtrain.repository.CoachRepository;
import com.example.bookingtrain.service.inter.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService implements ICoachService {

    private CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> getAll() {
        return coachRepository.findAll();
    }

    public Coach getById(int id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    public boolean delete(int id) {
        Coach coach = coachRepository.findById(id).orElse(null);
        if (coach != null) {
            coach.setStatusCoach(0);
            return true;
        }
        return false;
    }
}
