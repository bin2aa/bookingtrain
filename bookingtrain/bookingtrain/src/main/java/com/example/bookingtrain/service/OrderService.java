package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Order;
import com.example.bookingtrain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository oderRepository;

    @Autowired
    public OrderService(OrderRepository oderRepository) {
        this.oderRepository = oderRepository;
    }

    public Order getById(Integer id) {
        return oderRepository.findById((long)id).orElse(null);
    }

    public List<Order> getAll() {
        return oderRepository.findAll();
    }

    public Order save(Order oder) {
        return oderRepository.save(oder);
    }

    public void delete(Order oder) {
        oderRepository.delete(oder);
    }
}
