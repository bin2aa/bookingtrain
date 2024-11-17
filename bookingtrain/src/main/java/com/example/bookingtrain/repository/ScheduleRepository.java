package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Schedule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Page<Schedule> findAll(Pageable pageable);

    @Query("SELECT s FROM Schedule s WHERE s.train.trainName LIKE %:trainName%")
    Page<Schedule> findByTrainNameContaining(String trainName, Pageable pageable);
}
