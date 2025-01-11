package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TrainScheduleDTO {
    private String trainName;
    private String routeName;
    private LocalDateTime startDeparture;
    private LocalDateTime endDeparture;
    private double basePrice;

    public TrainScheduleDTO(String trainName, String routeName, LocalDateTime startDeparture,
            LocalDateTime endDeparture, double basePrice) {
        this.trainName = trainName;
        this.routeName = routeName;
        this.startDeparture = startDeparture;
        this.endDeparture = endDeparture;
        this.basePrice = basePrice;
    }
}