package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Booking;

import java.util.List;

public interface IBookingService {

    Booking getById(int id);
    List<Booking> getAll();
    Booking save(Booking book);
    boolean delete(int id);
}
