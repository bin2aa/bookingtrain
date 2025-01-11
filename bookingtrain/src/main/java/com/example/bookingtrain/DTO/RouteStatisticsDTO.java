package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteStatisticsDTO {
    private String routeName;
    private long runCount;
    private long trainCount;

    public RouteStatisticsDTO(String routeName, long runCount, long trainCount) {
        this.routeName = routeName;
        this.runCount = runCount;
        this.trainCount = trainCount;
    }
}