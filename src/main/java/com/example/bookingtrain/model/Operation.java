package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operationId;

    @Column(nullable = true)
    private String operationName;

    public Operation() {
    }

    public Operation(Integer operationId, String operationName) {
        this.operationId = operationId;
        this.operationName = operationName;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}