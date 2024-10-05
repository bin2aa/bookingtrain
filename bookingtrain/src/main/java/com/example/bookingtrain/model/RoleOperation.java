package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "roleoperation")
public class RoleOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(nullable = false)
    private Integer permissionId;

    @Column(nullable = true)
    private Integer operationId;

    @Column(nullable = true)
    private Integer statusId;

    public RoleOperation() {
    }

    public RoleOperation(Integer roleId, Integer permissionId, Integer operationId, Integer statusId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.operationId = operationId;
        this.statusId = statusId;
    }

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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}