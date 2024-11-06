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

 public Route save(Route route) {
// route.setIsActive(1);
 return repo.save(route);
 }

 public Route update(Route route) {
 return repo.save(route);
 }

 public Route delete(int id) {
 Route route = repo.getById(id);
// route.setIsActive(0);
 return repo.save(route);
 }
 }
