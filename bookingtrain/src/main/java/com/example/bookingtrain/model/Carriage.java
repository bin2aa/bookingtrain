package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "carriages")

public class Carriage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carriageID;

    @Column(nullable = false)
    private Integer carriageTypeID;

    @Column(nullable = false)
    private String carriageCode;

    @Column(nullable = false)
    private Integer carriageName;

    public Carriage() {
    }

    public Carriage(Integer carriageID, Integer carriageTypeID, String carriageCode, Integer carriageName) {
        this.carriageID = carriageID;
        this.carriageTypeID = carriageTypeID;
        this.carriageCode = carriageCode;
        this.carriageName = carriageName;
    }

    public Integer getCarriageID() {
        return carriageID;
    }

    public void setCarriageID(Integer carriageID) {
        this.carriageID = carriageID;
    }

    public Integer getCarriageTypeID() {
        return carriageTypeID;
    }

    public void setCarriageTypeID(Integer carriageTypeID) {
        this.carriageTypeID = carriageTypeID;
    }

    public String getCarriageCode() {
        return carriageCode;
    }

    public void setCarriageCode(String carriageCode) {
        this.carriageCode = carriageCode;
    }

    public Integer getCarriageName() {
        return carriageName;
    }

    public void setCarriageName(Integer carriageName) {
        this.carriageName = carriageName;
    }

}
