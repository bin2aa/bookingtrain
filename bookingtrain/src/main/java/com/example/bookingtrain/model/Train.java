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

    @Column(nullable = false)
    private String trainCode;

    @Column(nullable = false)
    private String trainName;

    @Column(nullable = false)
    private String image;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Integer statusTrain;

    @Column(nullable = false)
    private int numberOfSeat;

    @Transient
    public String getImagePath() {
        if (image == null || trainId == null) return null;

        return "/static/media/img/trainImg" + "/" + image;
    }
}