package com.example.train.Model;
import com.example.train.Model.StationModel;
import com.example.train.Model.TrainModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")

public class RouteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;
    private Date timetoDeparture;
    private Date timetoArrival;

    @ManyToOne
    @JoinColumn(name = "trainId")
    private TrainModel train;

    @ManyToOne
    @JoinColumn(name = "stationDepartureId")
    private StationModel stationDeparture;

    @ManyToOne
    @JoinColumn(name = "stationArrivalId")
    private StationModel stationArrival;

}
