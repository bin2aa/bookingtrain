package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "seattype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatTypeId;

    @Column(nullable = false)
    private String seatTypeName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private Integer price;
}