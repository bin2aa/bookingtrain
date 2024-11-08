package com.example.train.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class PassengerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerId;
    private String fullName;
    private LocalDate dateOfBirth;
    private String identityId;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "objectId")
    private ObjectModel objectId;

}
