package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carriages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carriage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carriageId;

    @Column(nullable = false)
    private Integer carriageTypeId;

    @Column(nullable = false)
    private String carriageCode;

    @Column(nullable = false)
    private String carriageName;

    @ManyToOne
    @JoinColumn(name = "carriageTypeId", insertable = false, updatable = false)
    private CarriageType carriageType;
}