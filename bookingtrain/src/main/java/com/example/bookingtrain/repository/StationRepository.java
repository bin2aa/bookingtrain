package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Station;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    Page<Station> findAll(Pageable pageable);

    Page<Station> findByStationNameContaining(String stationName, Pageable pageable);
}
