package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Coache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoacheRepository extends JpaRepository<Coache, Integer> {

}