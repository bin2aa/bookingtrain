package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Station;

import java.util.List;

public interface IStationService {

    Station getById(int id);
    List<Station> getAll();
    Station createStation(Station station);
    Station updateStation(int id, Station station);
    void deleteStation(int id);
}
