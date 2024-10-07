package com.example.bookingtrain.model;

import javax.persistence.*;

@Entity
@Table(name = "objects")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectId;

    @Column(nullable = true)
    private String objectName;

    @Column(nullable = true)
    private Integer price;

    public Object() {
    }

    public Object(Integer objectId, String objectName, Integer price) {
        this.objectId = objectId;
        this.objectName = objectName;
        this.price = price;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}