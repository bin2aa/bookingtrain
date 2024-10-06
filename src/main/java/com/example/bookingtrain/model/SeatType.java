package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "seattype")
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatTypeId;

    @Column(nullable = true)
    private String seatTypeName;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Integer price;

    public SeatType() {
    }

    public SeatType(Integer seatTypeId, String seatTypeName, String description, Integer price) {
        this.seatTypeId = seatTypeId;
        this.seatTypeName = seatTypeName;
        this.description = description;
        this.price = price;
    }

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public String getSeatTypeName() {
        return seatTypeName;
    }

    public void setSeatTypeName(String seatTypeName) {
        this.seatTypeName = seatTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}