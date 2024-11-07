package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "statusseat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusSeatId;

    @Column(nullable = false)
    private String statusSeatName;
}