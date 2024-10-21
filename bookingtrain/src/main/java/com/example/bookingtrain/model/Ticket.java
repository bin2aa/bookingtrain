package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @Column(nullable = false)
    private Integer passengerId;

    @Column(nullable = false)
    private Integer legId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfBooking;

    @ManyToOne
    @JoinColumn(name = "passengerId", insertable = false, updatable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "legId", insertable = false, updatable = false)
    private Leg leg;
}