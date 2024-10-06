package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
