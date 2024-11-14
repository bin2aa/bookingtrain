package com.example.bookingtrain.service;

import com.example.bookingtrain.DTO.InforReservationDTO;
import com.example.bookingtrain.repository.InfoReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoReservationService {
    @Autowired
    public InfoReservationRepository infoReservationRepository;

    public List<InforReservationDTO> getReservations(Long stationDepartureId, Long stationArrivalId, String travelDate) {
        return infoReservationRepository.findAvailableSeatsByStations(stationDepartureId, stationArrivalId, travelDate);
    }
}
