package com.example.bookingtrain.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerId;

    @Column(nullable = true)
    private String passengerName;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private Integer objectId;

    @ManyToOne
    @JoinColumn(name = "objectId", insertable = false, updatable = false)
    private Object object;
}