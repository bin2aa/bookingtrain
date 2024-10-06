package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @Column(nullable = true)
    private String stationCode;

    @Column(nullable = true)
    private String stationName;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String description;

    public Station() {
    }

    public Station(Long stationId, String stationCode, String stationName, String image, String description) {
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.image = image;
        this.description = description;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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