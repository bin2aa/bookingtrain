package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
