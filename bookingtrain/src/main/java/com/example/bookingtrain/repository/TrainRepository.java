package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Train;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

    Train findByTrainName(String trainName);

    Page<Train> findAll(Pageable pageable);

    Page<Train> findByTrainNameContaining(String trainName, Pageable pageable);

}
