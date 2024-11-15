package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Object;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object, Integer> {

    Page<Object> findAll(Pageable pageable);
}
