package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "carriagetype")

public class CarriageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carriageTypeID;

    @Column(nullable = false)
    private String carriageTypeName;

    @Column(nullable = false)
    private String description;

    public CarriageType() {
    }

    public CarriageType(Integer carriageTypeID, String carriageTypeName, String description) {
        this.carriageTypeID = carriageTypeID;
        this.carriageTypeName = carriageTypeName;
        this.description = description;
    }

    public Integer getCarriageTypeID() {
        return carriageTypeID;
    }

    public void setCarriageTypeID(Integer carriageTypeID) {
        this.carriageTypeID = carriageTypeID;
    }

    public String getCarriageTypeName() {
        return carriageTypeName;
    }

    public void setCarriageTypeName(String carriageTypeName) {
        this.carriageTypeName = carriageTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
