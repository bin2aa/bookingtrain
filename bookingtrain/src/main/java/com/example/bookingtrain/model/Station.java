package com.example.bookingtrain.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "stations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;

    @Column(nullable = true)
    private String stationName;

    @Column(nullable = true)
    private String stationCode;

    @Column(nullable = true)
    private int statusStation;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String image;
}