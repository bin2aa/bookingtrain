package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    private Double price;
}