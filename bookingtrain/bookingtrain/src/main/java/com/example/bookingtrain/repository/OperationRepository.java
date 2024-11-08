package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    Operation findByOperationName(String fullName);
}
