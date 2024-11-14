package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Coach;

import java.util.List;

public interface ICoachService {

    List<Coach> getAll();

    Coach getById(int id);

    Coach save(Coach coach);

    boolean delete(int id);
}
