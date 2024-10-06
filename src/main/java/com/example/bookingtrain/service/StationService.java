package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StationService {

    public StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public List<Station> getAll(){
        return stationRepository.findAll();
    }

    public Station getById(long id){
        return stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station with id " + id + " not found"));
    }

    public Station create(Station station){
        return stationRepository.save(station);
    }

    public Station update(Station station, long id){
        Station stationTemp = stationRepository.findById(id)
                .orElse(null);

        if(stationTemp == null) throw new EntityNotFoundException("Station with id " + id + " not found");

        stationTemp.setStationCode(station.getStationCode());
        stationTemp.setStationName(station.getStationName());
        stationTemp.setImage(station.getImage());
        stationTemp.setDescription(station.getDescription());
        return stationRepository.save(stationTemp);
    }

    public void delete(long id){
        getById(id); // Kiểm tra null
        stationRepository.deleteById(id);
    }
}
