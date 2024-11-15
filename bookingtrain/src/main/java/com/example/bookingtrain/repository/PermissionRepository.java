package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Permission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Page<Permission> findAll(Pageable pageable);
}
