package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;

    @Column(name="trainCode",nullable = true)
    private String trainCode;

    @Column(name="trainName",nullable = true)
    private String trainName;

    @Column(name="image",nullable = true)
    private String image;

    @Column(name="description" ,nullable = true)
    private String description;

    public Train() {
    }

    public Train(Long trainId, String trainCode, String trainName, String image, String description) {
        this.trainId = trainId;
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.image = image;
        this.description = description;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
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