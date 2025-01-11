package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevenueStatistic {
    private int day;
    private int month;
    private int year;
    private Double revenue;

    public RevenueStatistic(int month, Double revenue) {
        this.month = month;
        this.revenue = revenue;
    }

    public RevenueStatistic(int day, int month, int year, Double revenue) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.revenue = revenue;
    }
}