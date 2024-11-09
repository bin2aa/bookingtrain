package com.example.bookingtrain.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @Column(nullable = true)
    private String routeName;

    @Column(nullable = true)
    private Integer stationDepartureId;

    @Column(nullable = true)
    private Integer stationArrivalId;

    @Column(nullable = true)
    private Integer trainId;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", insertable = false, updatable = false)
    private Station stationDeparture;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", insertable = false, updatable = false)
    private Station stationArrival;

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = false, updatable = false)
    private Train train;
}