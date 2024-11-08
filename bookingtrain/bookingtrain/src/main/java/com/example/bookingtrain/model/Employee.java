package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;
}