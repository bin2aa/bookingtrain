package com.example.bookingtrain.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "stations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;

    @Column(nullable = false)
    private String stationCode;

    @Column(nullable = false)
    private String stationName;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private Integer statusStation;
}