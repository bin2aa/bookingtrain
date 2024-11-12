package com.example.bookingtrain.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
@Entity
@Table(name = "objects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectId;

    @Column(nullable = false)
    private String objectName;

    @Column(nullable = false)
    private Integer price;

}
