package com.example.train.Service;

import com.example.train.Model.StationModel;
import com.example.train.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    public List<StationModel> getAllStations() {
        return stationRepository.findAll();
    }
}
