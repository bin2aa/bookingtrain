package com.example.train.Model;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TicketInfoModel {
    private String trainCode;
    private String trainName;
    private String stationDeparture;
    private String stationArrival;
    private String totalTravelTime;
    private int price;
    private String timeDepartureFormatted;
    private String timeArrivalFormatted;
    private String departureDate;
    private String arrivalDate;
    private String timeDeparture;
    private  String timeArrival;

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getTimeArrivalFormatted() {
        return timeArrivalFormatted;
    }

    public void setTimeArrivalFormatted(String timeArrivalFormatted) {
        this.timeArrivalFormatted = timeArrivalFormatted;
    }

    public String getTimeDepartureFormatted() {
        return timeDepartureFormatted;
    }

    public void setTimeDepartureFormatted(String timeDepartureFormatted) {
        this.timeDepartureFormatted = timeDepartureFormatted;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getStationDeparture() {
        return stationDeparture;
    }

    public void setStationDeparture(String stationDeparture) {
        this.stationDeparture = stationDeparture;
    }

    public String getStationArrival() {
        return stationArrival;
    }

    public void setStationArrival(String stationArrival) {
        this.stationArrival = stationArrival;
    }

    public String getTotalTravelTime() {
        return totalTravelTime;
    }

    public void setTotalTravelTime(String totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }
}
