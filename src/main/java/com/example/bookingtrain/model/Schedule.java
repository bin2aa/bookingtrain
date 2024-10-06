package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(nullable = true)
    private Integer stationDepartmentId;

    @Column(nullable = true)
    private Integer stationsArrivalId;

    @Column(nullable = true)
    private Integer trainId;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeToDepartment;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeToArrival;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeToCreateSchedule;

    @Column(nullable = true)
    private Integer employeeId;

    public Schedule() {
    }

    public Schedule(Integer scheduleId, Integer stationDepartmentId, Integer stationsArrivalId, Integer trainId, Date timeToDepartment, Date timeToArrival, Date timeToCreateSchedule, Integer employeeId) {
        this.scheduleId = scheduleId;
        this.stationDepartmentId = stationDepartmentId;
        this.stationsArrivalId = stationsArrivalId;
        this.trainId = trainId;
        this.timeToDepartment = timeToDepartment;
        this.timeToArrival = timeToArrival;
        this.timeToCreateSchedule = timeToCreateSchedule;
        this.employeeId = employeeId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getStationDepartmentId() {
        return stationDepartmentId;
    }

    public void setStationDepartmentId(Integer stationDepartmentId) {
        this.stationDepartmentId = stationDepartmentId;
    }

    public Integer getStationsArrivalId() {
        return stationsArrivalId;
    }

    public void setStationsArrivalId(Integer stationsArrivalId) {
        this.stationsArrivalId = stationsArrivalId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Date getTimeToDepartment() {
        return timeToDepartment;
    }

    public void setTimeToDepartment(Date timeToDepartment) {
        this.timeToDepartment = timeToDepartment;
    }

    public Date getTimeToArrival() {
        return timeToArrival;
    }

    public void setTimeToArrival(Date timeToArrival) {
        this.timeToArrival = timeToArrival;
    }

    public Date getTimeToCreateSchedule() {
        return timeToCreateSchedule;
    }

    public void setTimeToCreateSchedule(Date timeToCreateSchedule) {
        this.timeToCreateSchedule = timeToCreateSchedule;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}