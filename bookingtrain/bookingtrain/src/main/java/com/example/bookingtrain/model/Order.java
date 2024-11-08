package com.example.bookingtrain.model;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private Integer employeeId;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double totalPrice;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    private Employee employee;
}