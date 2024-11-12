package com.example.bookingtrain.service;

import com.example.bookingtrain.model.SeatType;
import com.example.bookingtrain.repository.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;

    @Autowired
    public SeatTypeService(SeatTypeRepository seatTypeRepository) {
        this.seatTypeRepository = seatTypeRepository;
    }

    public List<SeatType> getAllSeatTypes() {
        return seatTypeRepository.findAll();
    }

    public SeatType getSeatTypeById(Integer id) {
        return seatTypeRepository.findById(id).orElse(null);
    }

    public SeatType createSeatType(SeatType seatType) {
        return seatTypeRepository.save(seatType);
    }

    public SeatType updateSeatType(SeatType seatType) {
        return seatTypeRepository.saveAndFlush(seatType);
    }

    public void deleteSeatType(Integer id) {
        seatTypeRepository.deleteById(id);
    }
}