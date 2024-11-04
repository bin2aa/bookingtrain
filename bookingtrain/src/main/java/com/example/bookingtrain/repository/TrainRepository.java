package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

    Train findByTrainName(String trainName);
    Train findByTrainCode(String trainCode);
}
