package com.example.bookingtrain.service;

import com.example.bookingtrain.DTO.InforReservationDTO;
import com.example.bookingtrain.repository.InfoReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InfoReservationService {
    @Autowired
    public InfoReservationRepository infoReservationRepository;

    public Page<InforReservationDTO> getReservationsWithPagination(
            Long stationDepartureId,
            Long stationArrivalId,
            String travelDate,
            Pageable pageable) {
        return infoReservationRepository.findAvailableSeatsByStationsWithPagination(
                stationDepartureId, stationArrivalId, travelDate, pageable);
    }

}