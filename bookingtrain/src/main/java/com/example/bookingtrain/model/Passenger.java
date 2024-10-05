package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerId;

    @Column(nullable = true)
    private String fullName;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(nullable = true)
    private String identityId;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private Integer objectId;

    public Passenger() {
    }

    public Passenger(Integer passengerId, String fullName, Date dateOfBirth, String identityId, String phone,
            Integer objectId) {
        this.passengerId = passengerId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.identityId = identityId;
        this.phone = phone;
        this.objectId = objectId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
}