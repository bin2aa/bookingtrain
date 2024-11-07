package com.example.train.Model;
import com.example.train.Model.CarriageTypeModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carriages")

public class CarriageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carriageId;
    private String carriageCode;
    private String carriageName;

    @ManyToOne
    @JoinColumn(name = "carriageTypeId")
    private CarriageTypeModel carriageType;
}
