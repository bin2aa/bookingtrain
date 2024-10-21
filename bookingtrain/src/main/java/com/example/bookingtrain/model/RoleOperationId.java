package com.example.bookingtrain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class RoleOperationId implements Serializable {
    private Integer roleId;
    private Integer permissionId;
    private Integer operationId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public RoleOperationId() {
    }

    public RoleOperationId(Integer roleId, Integer permissionId, Integer operationId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.operationId = operationId;
    }
}
