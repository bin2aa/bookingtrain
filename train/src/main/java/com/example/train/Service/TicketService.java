package com.example.train.Service;

import com.example.train.DTO.SummaryDTO;
import com.example.train.Model.TicketInfoModel;
import com.example.train.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    public TicketRepository ticketRepository;

    // Fetch available seats based on departure and arrival station IDs
    public List<TicketInfoModel> findAvailableSeatsByStations(Long stationDepartureId, Long stationArrivalId,int numberOfAdults, int numberOfKids) {
        return ticketRepository.findAvailableSeatsByStations(stationDepartureId, stationArrivalId, numberOfAdults, numberOfKids);
    }
    public List<SummaryDTO> findSummaryInfo(String trainCode, String stationDeparture, String stationArrival, String departureTime, String arrivalTime,int numberOfAdults, int numberOfKids) {
        return ticketRepository.findSummaryInfo(trainCode, stationDeparture, stationArrival, departureTime, arrivalTime, numberOfAdults, numberOfKids);
    }
}
