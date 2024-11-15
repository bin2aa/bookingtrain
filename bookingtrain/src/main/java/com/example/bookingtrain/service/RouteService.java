package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Route;
import com.example.bookingtrain.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Page<Route> getAllRoutes(Pageable pageable) {
        return routeRepository.findAll(pageable);
    }

    public Route getRouteById(Integer id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route createRoute(Route route) {
        if (route.getStatusRoute() == 0) {
            route.setStatusRoute(1);
        }
        return routeRepository.save(route);
    }

    public Route updateRoute(Route route) {
        Route existingRoute = routeRepository.findById(route.getRouteId()).orElse(null);
        if (existingRoute != null) {
            existingRoute.setRouteName(route.getRouteName());
            existingRoute.setStationDepartureId(route.getStationDepartureId());
            existingRoute.setStationArrivalId(route.getStationArrivalId());
            existingRoute.setTrainId(route.getTrainId());
            existingRoute.setStatusRoute(route.getStatusRoute());
            return routeRepository.saveAndFlush(existingRoute);
        }
        return null;
    }

    public void deleteRoute(Integer id) {
        routeRepository.deleteById(id);
    }
}