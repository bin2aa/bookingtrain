package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByFullName(String fullName);
}
