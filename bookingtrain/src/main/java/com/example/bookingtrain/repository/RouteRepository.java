package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Route;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    Page<Route> findAll(Pageable pageable);
}