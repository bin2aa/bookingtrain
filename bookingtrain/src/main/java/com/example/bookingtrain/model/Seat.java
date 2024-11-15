package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    private Integer coacheId;

    @Column(nullable = true)
    private Integer seatTypeId;

    @ManyToOne
    @JoinColumn(name = "coacheId", insertable = false, updatable = false)
    private Coache coache;

    @ManyToOne
    @JoinColumn(name = "seatTypeId", insertable = false, updatable = false)
    private SeatType seatType;
}