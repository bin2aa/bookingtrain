package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "roleOperation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOperation {

    @EmbeddedId
    private RoleOperationId id;

    @Column(name = "statusId")
    private Integer statusId;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permissionId", insertable = false, updatable = false)
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "operationId", insertable = false, updatable = false)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "statusId", insertable = false, updatable = false)
    private StatusRoleOperation statusRoleOperation;
}