package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Route;
import com.example.bookingtrain.repository.RouteRepository;
import com.example.bookingtrain.service.inter.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements IRouteService {

    private RouteRepository repo;

    @Autowired
    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    public List<Route> getAllRoutes() {
        return repo.findAll();
    }

    public Route getById(int id) {
        return repo.findById(id).orElse(null);
    }
}
