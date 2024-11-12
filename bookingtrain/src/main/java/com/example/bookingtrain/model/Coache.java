package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "coaches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coacheId;

    @Column(nullable = false)
    private int statusCoache;

    @Column(nullable = true)
    private Integer trainId;

    @Column(nullable = true)
    private String coacheName;

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = false, updatable = false)
    private Train train;
}