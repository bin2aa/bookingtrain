package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;

import lombok.*;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @Column(nullable = false)
    private Integer passengerId;

    @Column(nullable = false)
    private Integer legId;

    @Column(nullable = false)
    private Integer stationDepartureId;

    @Column(nullable = false)
    private Integer stationArrivalId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfBooking;

    @Column(nullable = false)
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "passengerId", insertable = false, updatable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "legId", insertable = false, updatable = false)
    private Leg leg;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", referencedColumnName  = "stationId", insertable = false, updatable = false)
    private Station stationDepartured;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", referencedColumnName  = "stationId", insertable = false, updatable = false)
    private Station stationArrival;

}