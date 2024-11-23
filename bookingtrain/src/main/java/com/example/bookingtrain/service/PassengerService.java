package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Passenger;
import com.example.bookingtrain.repository.PassengerRepository;
import com.example.bookingtrain.service.inter.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService implements IPassengerService {

    private PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger getById(Integer id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    public Page<Passenger> getAllPassengers(Pageable pageable) {
        return passengerRepository.findAll(pageable);
    }

    public Page<Passenger> searchPassengersByName(String passengerName, Pageable pageable) {
        return passengerRepository.findByPassengerNameContaining(passengerName, pageable);
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void delete(int id) {
        passengerRepository.deleteById(id);
    }

    public Integer findObjectPriceByObjectId(Integer objectId) {
        return passengerRepository.findObjectPriceByObjectId(objectId);
    }
}