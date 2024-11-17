package com.example.bookingtrain.DTO;

public class StationArrivalStatistic {
    private String stationArrivalId;
    private long arrivalCount;

    // Constructor
    public StationArrivalStatistic(String stationArrivalId, long arrivalCount) {
        this.stationArrivalId = stationArrivalId;
        this.arrivalCount = arrivalCount;
    }

    // Getters and Setters
    public String getStationArrivalId() {
        return stationArrivalId;
    }

    public void setStationArrivalId(String stationArrivalId) {
        this.stationArrivalId = stationArrivalId;
    }

    public long getArrivalCount() {
        return arrivalCount;
    }

    public void setArrivalCount(long arrivalCount) {
        this.arrivalCount = arrivalCount;
    }
}

