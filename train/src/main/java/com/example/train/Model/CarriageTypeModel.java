package com.example.train.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carriageType")

public class CarriageTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carriageTypeId;
    private String carriageTypeName;
    private String description;
    private int price;
}
