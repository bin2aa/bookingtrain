package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Coache;

import java.util.List;

public interface ICoacheService {

    List<Coache> getAll();

    Coache getById(int id);

    Coache save(Coache coache);

    boolean delete(int id);
}
