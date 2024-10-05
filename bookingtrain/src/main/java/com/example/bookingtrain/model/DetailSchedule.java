package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "detailschedule")
public class DetailSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(nullable = false)
    private Integer seatId;

    @Column(nullable = true)
    private Integer statusSeatId;

    public DetailSchedule() {
    }

    public DetailSchedule(Integer scheduleId, Integer seatId, Integer statusSeatId) {
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.statusSeatId = statusSeatId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getStatusSeatId() {
        return statusSeatId;
    }

    public void setStatusSeatId(Integer statusSeatId) {
        this.statusSeatId = statusSeatId;
    }
}