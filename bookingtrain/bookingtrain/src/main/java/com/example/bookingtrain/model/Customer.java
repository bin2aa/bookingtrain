package com.example.bookingtrain.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Integer customerID;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private String roleId;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;
}