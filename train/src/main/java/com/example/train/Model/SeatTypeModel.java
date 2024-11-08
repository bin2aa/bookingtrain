package com.example.train.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seatType")

public class SeatTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatTypeId;
    private String seatTypeName;
    private String description;
    private int price;
}
