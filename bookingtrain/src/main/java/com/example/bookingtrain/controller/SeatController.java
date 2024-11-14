package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.DetailSeatDTO;
import com.example.bookingtrain.service.DetailSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private DetailSeatService seatService;

    // Corrected method signature and usage of ResponseEntity
    @GetMapping("/all")
    public ResponseEntity<List<DetailSeatDTO>> getAllSeats(@RequestParam int trainId) {
        try {
            List<DetailSeatDTO> seats = seatService.findSeatsByTrainId(trainId);
            return ResponseEntity.ok(seats); // Return JSON with 200 OK
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Return error response if an exception occurs
        }
    }
}
