package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", insertable = false, updatable = false)
    private Station stationDeparture;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", insertable = false, updatable = false)
    private Station stationArrival;
}