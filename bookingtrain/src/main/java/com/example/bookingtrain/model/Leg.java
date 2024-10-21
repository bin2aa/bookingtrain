package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "legs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer legId;

    @Column(nullable = false)
    private Integer statusSeatId;

    @Column(nullable = false)
    private Integer stationDepartureId;

    @Column(nullable = false)
    private Integer stationArrivalId;

    @Column(nullable = false)
    private Integer trainId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeDeparture;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeArrival;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId", insertable = false, updatable = false)
    private Station stationDeparture;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId", insertable = false, updatable = false)
    private Station stationArrival;

    @ManyToOne
    @JoinColumn(name = "statusSeatId", insertable = false, updatable = false)
    private StatusSeat statusSeat;

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = false, updatable = false)
    private Train train;
}