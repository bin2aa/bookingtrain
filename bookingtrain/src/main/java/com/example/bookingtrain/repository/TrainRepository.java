package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {

}
