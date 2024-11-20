package com.example.bookingtrain.DTO;

public class InforReservationDTO {

    private int trainId;
    private String trainCode;
    private String StationDeparture;
    private String StationArrival;
    private String timeDepartureFormatted;
    private String timeArrivalFormatted;
    private String dateDepartureFormatted;
    private String dateArrivalFormatted;
    private int isAvailable;
    private String totalTime;
    private String scheduleArrival;
    private String scheduleDeparture;
    private int scheduleId;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleArrival() {
        return scheduleArrival;
    }

    public void setScheduleArrival(String scheduleArrival) {
        this.scheduleArrival = scheduleArrival;
    }

    public String getScheduleDeparture() {
        return scheduleDeparture;
    }

    public void setScheduleDeparture(String scheduleDeparture) {
        this.scheduleDeparture = scheduleDeparture;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getStationDeparture() {
        return StationDeparture;
    }

    public void setStationDeparture(String stationDeparture) {
        StationDeparture = stationDeparture;
    }

    public String getStationArrival() {
        return StationArrival;
    }

    public void setStationArrival(String stationArrival) {
        StationArrival = stationArrival;
    }

    public String getTimeDepartureFormatted() {
        return timeDepartureFormatted;
    }

    public void setTimeDepartureFormatted(String timeDepartureFormatted) {
        this.timeDepartureFormatted = timeDepartureFormatted;
    }

    public String getTimeArrivalFormatted() {
        return timeArrivalFormatted;
    }

    public void setTimeArrivalFormatted(String timeArrivalFormatted) {
        this.timeArrivalFormatted = timeArrivalFormatted;
    }

    public String getDateDepartureFormatted() {
        return dateDepartureFormatted;
    }

    public void setDateDepartureFormatted(String dateDepartureFormatted) {
        this.dateDepartureFormatted = dateDepartureFormatted;
    }

    public String getDateArrivalFormatted() {
        return dateArrivalFormatted;
    }

    public void setDateArrivalFormatted(String dateArrivalFormatted) {
        this.dateArrivalFormatted = dateArrivalFormatted;
    }

}