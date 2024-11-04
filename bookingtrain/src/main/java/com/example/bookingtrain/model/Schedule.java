package com.example.bookingtrain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(nullable= false)
    private Integer trainId;

    @Column(nullable = false)
    private Integer stationDepartureId;

    @Column(nullable = false)
    private Integer stationArrivalId;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", referencedColumnName  = "stationId", insertable = false, updatable = false)
    private Station stationDepartured;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", referencedColumnName  = "stationId", insertable = false, updatable = false)
    private Station stationArrival;

    @Column(nullable = false)
    private int statusSchedule;
}
