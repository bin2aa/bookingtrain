package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationArrivalStatistic {
    private String stationArrivalId;
    private int year;
    private int month;
    private int day;
    private long arrivalCount;

    public StationArrivalStatistic(String stationArrivalId, int year, int month, int day, long arrivalCount) {
        this.stationArrivalId = stationArrivalId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.arrivalCount = arrivalCount;
    }
}