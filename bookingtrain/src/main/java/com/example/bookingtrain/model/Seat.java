package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    @Column(nullable = true)
    private String seatCode;

    @Column(nullable = true)
    private Integer seatTypeId;

    @Column(nullable = true)
    private Integer carriageId;

    public Seat() {
    }

    public Seat(Integer seatId, String seatCode, Integer seatTypeId, Integer carriageId) {
        this.seatId = seatId;
        this.seatCode = seatCode;
        this.seatTypeId = seatTypeId;
        this.carriageId = carriageId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public Integer getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(Integer carriageId) {
        this.carriageId = carriageId;
    }
}