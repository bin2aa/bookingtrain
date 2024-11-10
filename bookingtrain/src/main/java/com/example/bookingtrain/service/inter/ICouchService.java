package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Couch;

import java.util.List;

public interface ICouchService {

    List<Couch> getAll();
    Couch getById(int id);
    Couch save(Couch couch);
    boolean delete(int id);
}
