package com.example.train.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "objects")
public class ObjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int objectId;
    private String objectName;
    private String price;

}
