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
    private InfoReservationRepository reservationRepository;

    public Page<InforReservationDTO> getReservationsWithPagination(Long sd, Long sa, String da, Pageable pageable) {
        return reservationRepository.findAvailableSeatsByStationsWithPagination(sd, sa, da, pageable);
    }
}