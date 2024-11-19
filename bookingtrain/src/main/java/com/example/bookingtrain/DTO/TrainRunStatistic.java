package com.example.bookingtrain.DTO;

import lombok.Getter;

@Getter
public class TrainRunStatistic {
    // Getters and Setters
    private int month;
    private int year;
    private String trainCode;
    private long totalRuns;

    // Constructor
    public TrainRunStatistic(int month, int year, String trainCode, long totalRuns) {
        this.month = month;
        this.year = year;
        this.trainCode = trainCode;
        this.totalRuns = totalRuns;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTrainId(String trainCode) {
        this.trainCode = trainCode;
    }

    public void setTotalRuns(long totalRuns) {
        this.totalRuns = totalRuns;
    }
}