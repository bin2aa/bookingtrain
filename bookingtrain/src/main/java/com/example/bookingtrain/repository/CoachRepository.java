package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {

}