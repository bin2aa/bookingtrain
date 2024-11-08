package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Passenger;

import java.util.List;

public interface IPassengerService {

    Passenger getById(Integer id);

    List<Passenger> getAll();

    Passenger save(Passenger passenger);

    void delete(Passenger passenger);
}
