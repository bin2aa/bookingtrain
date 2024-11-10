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
    private Integer coachId;

    @ManyToOne
    @JoinColumn(name = "coachId", insertable = false, updatable = false)
    private Coache coach;
}