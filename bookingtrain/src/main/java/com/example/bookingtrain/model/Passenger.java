package com.example.bookingtrain.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String identityId;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer objectId;

    @ManyToOne
    @JoinColumn(name = "objectId", insertable = false, updatable = false)
    private Object object;
}