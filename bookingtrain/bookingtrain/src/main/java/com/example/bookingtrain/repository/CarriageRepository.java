package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Carriage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarriageRepository extends JpaRepository<Carriage, Long> {
    Carriage findByCarriageName(String carriageName);
}
