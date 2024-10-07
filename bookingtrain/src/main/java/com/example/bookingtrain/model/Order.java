package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(nullable = true)
    private Integer passengerId;

    @Column(nullable = true)
    private Integer employeeId;

    @Column(nullable = true)
    private Integer price;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeToOrder;

    public Order() {
    }

    public Order(Integer orderId, Integer passengerId, Integer employeeId, Integer price, Date timeToOrder) {
        this.orderId = orderId;
        this.passengerId = passengerId;
        this.employeeId = employeeId;
        this.price = price;
        this.timeToOrder = timeToOrder;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getTimeToOrder() {
        return timeToOrder;
    }

    public void setTimeToOrder(Date timeToOrder) {
        this.timeToOrder = timeToOrder;
    }
}