package com.example.train.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stations")
public class StationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;
    private String stationCode;
    private String stationName;
    private String image;
    private String description;
    private int statusStation;
}
