package com.example.bookingtrain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date departureTime;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date arrivalTime;

    @ManyToOne
    @JoinColumn(name = "routeId", insertable = false, updatable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = false, updatable = false)
    private Train train;

    @Column
    private int statusSchedule;
}
