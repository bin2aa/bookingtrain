package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "statusseat")
public class StatusSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusSeatId;

    @Column(nullable = true)
    private String statusSeatName;

    public StatusSeat() {
    }

    public StatusSeat(Integer statusSeatId, String statusSeatName) {
        this.statusSeatId = statusSeatId;
        this.statusSeatName = statusSeatName;
    }

    public Integer getStatusSeatId() {
        return statusSeatId;
    }

    public void setStatusSeatId(Integer statusSeatId) {
        this.statusSeatId = statusSeatId;
    }

    public String getStatusSeatName() {
        return statusSeatName;
    }

    public void setStatusSeatName(String statusSeatName) {
        this.statusSeatName = statusSeatName;
    }
}