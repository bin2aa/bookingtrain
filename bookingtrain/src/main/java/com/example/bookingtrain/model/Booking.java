package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @Column(nullable = true)
    private Integer userId;

    @Column(nullable = true)
    private Integer employeeId;

    @Column(nullable = true)
    private Double total;

    @Column(nullable = true)
    private java.util.Date dateBooking;

    @Column(nullable = true)
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "scheduleId", insertable = false, updatable = false)
    private Schedule schedule;

    @Column
    private int statusBooking;
}