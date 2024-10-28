package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Object;

import java.util.List;

public interface IObjectService {

    Object getById(Integer id);
    List<Object> getAll();
    Object save(Object object);
    void delete(Integer id);
}
