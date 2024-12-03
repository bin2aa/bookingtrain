package com.example.bookingtrain.DTO;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RevenueStatistic {
    // Getters and Setters
    private int month;
    private Double revenue;

    public RevenueStatistic(int month, Double revenue) {
        this.month = month;
        this.revenue = revenue;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
