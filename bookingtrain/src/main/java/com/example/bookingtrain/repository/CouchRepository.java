package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Couch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouchRepository extends JpaRepository<Couch, Integer> {

}
