package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.RoleOperation;
import com.example.bookingtrain.model.RoleOperationId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleOperationRepository extends JpaRepository<RoleOperation, RoleOperationId> {

    // sấp xếp theo roleId, permissionId, operationId
    @Query("SELECT ro FROM RoleOperation ro ORDER BY ro.id.roleId, ro.id.permissionId, ro.id.operationId")
    Page<RoleOperation> findAllOrderByRoleOperationId(Pageable pageable);

    @Query("SELECT ro FROM RoleOperation ro WHERE ro.id.roleId = :roleId AND ro.permission.permissionName = :permissionName AND ro.id.operationId = :operationId")
    RoleOperation findByRoleIdAndPermissionNameAndOperationId(@Param("roleId") int roleId,
            @Param("permissionName") String permissionName, @Param("operationId") int operationId);
}