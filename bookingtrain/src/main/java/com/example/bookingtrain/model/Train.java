package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainId;

    @Column(nullable = true)
    private String trainCode;

    @Column(nullable = true)
    private String trainName;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String description;

    public Train() {
    }

    public Train(Integer trainId, String trainCode, String trainName, String image, String description) {
        this.trainId = trainId;
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.image = image;
        this.description = description;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}