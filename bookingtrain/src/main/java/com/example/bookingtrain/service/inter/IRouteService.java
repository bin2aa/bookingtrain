package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Route;

import java.util.List;

public interface IRouteService {

    List<Route> getAllRoutes();
    Route getById(int id);
}
