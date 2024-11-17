package com.example.bookingtrain.DTO;

import lombok.Data;

@Data
public class TrainDetailsDTO {
    private int trainId;
    private String trainCode;
    private String stationDeparture;
    private String stationArrival;
    private String timeDeparture;
    private String dateDeparture;
    private String timeArrival;
    private String dateArrival;
}
