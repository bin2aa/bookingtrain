package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.repository.StationRepository;
import com.example.bookingtrain.service.inter.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService implements IStationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station getById(int id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Station updateStation(int id, Station updatedStation) {
        Station existingStation = stationRepository.findById(id).orElse(null);
        if (existingStation != null) {
            existingStation.setStationCode(updatedStation.getStationCode());
            existingStation.setStationName(updatedStation.getStationName());
            existingStation.setStatusStation(updatedStation.getStatusStation());
            existingStation.setDescription(updatedStation.getDescription());
            existingStation.setImage(updatedStation.getImage());
            return stationRepository.saveAndFlush(existingStation);
        }
        return null;
    }

    @Override
    public void deleteStation(int id) {
        stationRepository.deleteById(id);
    }
}