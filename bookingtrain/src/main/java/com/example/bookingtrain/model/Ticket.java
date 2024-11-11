package com.example.bookingtrain.model;

import javax.persistence.*;
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

    @Column(nullable = true)
    private Integer bookingId;

    @Column(nullable = true)
    private Integer seatId;

    @Column(nullable = true)
    private Integer passengerId;

    @Column
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "bookingId", insertable = false, updatable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "passengerId", insertable = false, updatable = false)
    private Passenger passenger;
}