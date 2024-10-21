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

    @Column(nullable = true, length = 255)
    private String seatCode;

    @Column(nullable = true)
    private Integer seatTypeId;

    @Column(nullable = true)
    private Integer carriageId;

    @ManyToOne
    @JoinColumn(name = "seatTypeId", insertable = false, updatable = false)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "carriageId", insertable = false, updatable = false)
    private Carriage carriage;
}