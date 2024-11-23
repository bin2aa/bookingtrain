package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Seat;
import com.example.bookingtrain.model.SeatType;
import com.example.bookingtrain.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Page<Seat> getAllSeats(Pageable pageable) {
        return seatRepository.findAll(pageable);
    }

    public Seat getSeatById(Integer id) {
        return seatRepository.findById(id).orElse(null);
    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat updateSeat(Seat seat) {
        return seatRepository.saveAndFlush(seat);
    }

    public void deleteSeat(Integer id) {
        seatRepository.deleteById(id);
    }

    public Double findSeatPriceBySeatId(Integer seatId) {
        return seatRepository.findSeatPriceBySeatId(seatId);
    }
}