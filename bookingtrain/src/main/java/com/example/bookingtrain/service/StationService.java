package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.repository.StationRepository;
import com.example.bookingtrain.service.inter.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService implements IStationService {

    private final StationRepository stationRepo;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepo = stationRepository;
    }

    @Override
    public Station getById(int id) {
        return stationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Station> getAll() {
        return stationRepo.findAll();
    }

    @Override
    public Station createStation(Station station) {
        station.setStatusStation(1);
        return stationRepo.save(station);
    }

    @Override
    public Station updateStation(int id, Station updatedStation) {
        Station existedStation = getById(id);
        if (existedStation != null) {
            existedStation.setStationCode(updatedStation.getStationCode());
            existedStation.setStationName(updatedStation.getStationName());
            existedStation.setStatusStation(1);
        }
        return existedStation;
    }

    public Station save(Station station) {
        return stationRepo.save(station);
    }

    @Override
    public void deleteStation(int id) {
        Station station = getById(id);
        if (station != null) {
            station.setStatusStation(0);
            stationRepo.save(station);
        }
    }

//    public List<Station> getALlBy


}
