package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "statusroleoperation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusRoleOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    @Column(nullable = true)
    private String statusName;
}