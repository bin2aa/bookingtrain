package com.example.train.Model;
import com.example.train.Model.SeatTypeModel;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")

public class SeatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;
    private String seatCode;

    @ManyToOne
    @JoinColumn(name = "seatTypeId")
    private SeatTypeModel seatType;

    @ManyToOne
    @JoinColumn(name = "carriageId")
    private CarriageModel carriage;
}
