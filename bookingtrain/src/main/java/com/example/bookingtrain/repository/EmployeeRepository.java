package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmployeeName(String employeeName);
    Employee findByUserId(Integer userId);

    Page<Employee> findAll(Pageable pageable);
}