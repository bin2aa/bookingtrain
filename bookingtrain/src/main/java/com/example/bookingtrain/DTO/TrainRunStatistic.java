package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainRunStatistic {
    private int day;
    private int month;
    private int year;
    private long count;

    public TrainRunStatistic(int day, int month, int year, long count) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.count = count;
    }
}