package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "statusroleoperation")
public class StatusRoleOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    @Column(nullable = true)
    private String statusName;

    public StatusRoleOperation() {
    }

    public StatusRoleOperation(Integer statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}