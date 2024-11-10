package com.example.bookingtrain.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "couchs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Couch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couchId;

    @Column(nullable = false)
    private Integer trainId;

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = false, updatable = false)
    private Train train;

    @Column(nullable = false)
    private int statusCouch;
}
