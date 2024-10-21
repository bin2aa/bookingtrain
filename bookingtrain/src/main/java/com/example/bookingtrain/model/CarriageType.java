package com.example.bookingtrain.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carriagetype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarriageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carriageTypeID")
    private Integer carriageTypeID;

    @Column(nullable = false)
    private String carriageTypeName;

    @Column(nullable = false)
    private String description;

}