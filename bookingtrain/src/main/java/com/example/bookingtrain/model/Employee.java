package com.example.bookingtrain.model;

import java.time.LocalDate;
import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(nullable = true)
    private String employeeName;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;
}