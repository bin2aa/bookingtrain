package com.example.bookingtrain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Entity
@Table(name = "operations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operationId;

    @Column(nullable = true)
    private String operationName;

}