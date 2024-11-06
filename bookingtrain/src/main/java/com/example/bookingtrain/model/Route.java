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

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateStart;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEnd;

    @Column(nullable = false)
    private Integer trainId;

    @Column(nullable = false)
    private int isActive;
    @ManyToOne
    @JoinColumn(name = "stationDepartureId", insertable = false, updatable = false)
    private Station stationDeparture;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", insertable = false, updatable = false)
    private Station stationArrival;
}