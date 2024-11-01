package com.example.bookingtrain.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @Column(nullable = false)
    private Integer stationDepartureId;

    @Column(nullable = false)
    private Integer stationArrivalId;

    @Column(nullable = false)
    private Integer trainId;

    @Column(nullable = false)
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", referencedColumnName  = "stationId", insertable = false, updatable = false)
    private Station stationDepartured;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", referencedColumnName  = "stationId", insertable = false, updatable = false)
    private Station stationArrival;

    @ManyToOne
    @JoinColumn(name = "trainId", referencedColumnName  = "trainId", insertable = false, updatable = false)
    private Train train;

}
