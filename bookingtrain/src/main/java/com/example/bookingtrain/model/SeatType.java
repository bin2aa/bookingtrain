package com.example.bookingtrain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
@Entity
@Table(name = "seattypes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatTypeId;

    @Column(nullable = true)
    private String seatTypeName;

    @Column(nullable = true)
    private int price;
}