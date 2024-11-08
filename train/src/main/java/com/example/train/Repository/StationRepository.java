package com.example.train.Repository;

import com.example.train.Model.StationModel;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

@Repository // Đảm bảo rằng annotation này được sử dụng
public interface StationRepository extends JpaRepository<StationModel, Integer> {

}

