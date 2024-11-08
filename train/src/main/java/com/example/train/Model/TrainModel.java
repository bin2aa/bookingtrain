package com.example.train.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trains")

public class TrainModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainId;
    private String trainCode;
    private String trainName;
    private String image;
    private String description;
    private int statusTrain;
}
