package com.example.bookingtrain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    @Column(nullable = true)
    private String seatNumber;

    @Column(nullable = true)
    private Integer coachId;

    @ManyToOne
    @JoinColumn(name = "coachId", insertable = false, updatable = false)
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "seatTypeId", insertable = false, updatable = false)
    private SeatType seatType;

}