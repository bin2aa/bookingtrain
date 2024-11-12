package com.example.bookingtrain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(name = "endDeparture")
    private Instant arrivalTime;

    @Column(name = "startDeparture")
    private Instant departureTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stationArrivalId")
    private Station stationArrival;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stationDepartureId")
    private Station stationDeparture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainId")
    private Train train;

    @Column
    private int statusSchedule;
}
