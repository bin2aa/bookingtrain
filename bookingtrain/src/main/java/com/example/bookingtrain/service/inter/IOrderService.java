package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Order;

import java.util.List;

public interface IOrderService {

    Order getById(Integer id);
    List<Order> getAll();
    Order save(Order order);
    void delete(Order order);
}
