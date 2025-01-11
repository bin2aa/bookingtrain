package com.example.bookingtrain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(nullable = true)
    private Integer routeId;

    @Column(nullable = true)
    private Integer trainId;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private java.util.Date endDeparture;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private java.util.Date startDeparture;

    @Column(nullable = true)
    private Integer stationDepartureId;

    @Column(nullable = true)
    private Integer stationArrivalId;

    @Column(nullable = true)
    private int statusSchedule;

    @ManyToOne
    @JoinColumn(name = "routeId", insertable = false, updatable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = false, updatable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", insertable = false, updatable = false)
    private Station stationDeparture;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", insertable = false, updatable = false)
    private Station stationArrival;

    @Transient
    private int passengerCount;

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public int getPassengerCount() {
        return passengerCount;
    }
}