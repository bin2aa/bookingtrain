package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShceduleRepository extends JpaRepository<Schedule, Integer> {

}