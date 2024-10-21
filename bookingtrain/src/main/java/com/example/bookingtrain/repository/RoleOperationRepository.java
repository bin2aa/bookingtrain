package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.RoleOperation;
import com.example.bookingtrain.model.RoleOperationId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleOperationRepository extends JpaRepository<RoleOperation, RoleOperationId> {

}
