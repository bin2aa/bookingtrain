package com.example.bookingtrain.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainScheduleDTO {
    private String trainName;
    private String routeName;
    private LocalDateTime startDeparture;
    private LocalDateTime endDeparture;
    private double basePrice;
}
