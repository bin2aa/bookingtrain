package com.example.bookingtrain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    // @Transient
    // private Integer discount; // Thêm thuộc tính giảm giá, không lưu vào database
}
