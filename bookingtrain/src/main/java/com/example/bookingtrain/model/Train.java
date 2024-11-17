package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "trains")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainId;

    @Column(nullable = true)
    private String trainName;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private Integer statusTrain = 1;

}