package com.example.bookingtrain.service;

import com.example.bookingtrain.DTO.DetailSeatDTO;
import com.example.bookingtrain.repository.DetailSeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DetailSeatService {
    @Autowired
    DetailSeatRepository detailSeatRepository;

    // public List<DetailSeatDTO> findSeatsByTrainId(int trainId) {
    // log.info(detailSeatRepository.findSeatsByTrainId(trainId).toString());
    // return detailSeatRepository.findSeatsByTrainId(trainId);
    // }

    public List<DetailSeatDTO> findSeatsByTrainId(int trainId, int scheduleId) {
        return detailSeatRepository.findAvailableSeatsByTrainAndSchedule(trainId, scheduleId);
    }

}