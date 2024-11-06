// package com.example.bookingtrain.service;

// import com.example.bookingtrain.model.Station;
// import com.example.bookingtrain.repository.StationRepository;
// import com.example.bookingtrain.service.inter.IStationService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class StationService implements IStationService {

// private final StationRepository stationRepo;

// @Autowired
// public StationService(StationRepository stationRepository) {
// this.stationRepo = stationRepository;
// }

// @Override
// public Station getById(int id) {
// return stationRepo.findById((long) id).orElse(null);
// }

// @Override
// public List<Station> getAll() {
// return stationRepo.findAll();
// }

// @Override
// public Station createStation(Station station) {
// station.setStatusStation(1);
// return stationRepo.save(station);
// }

// @Override
// public Station updateStation(int id, Station updatedStation) {
// Station existedStation = getById(id);
// if (existedStation != null) {
// existedStation.setStationCode(updatedStation.getStationCode());
// existedStation.setStationName(updatedStation.getStationName());
// existedStation.setImage(updatedStation.getImage());
// existedStation.setDescription(updatedStation.getDescription());
// existedStation.setStatusStation(1);
// }
// return existedStation;
// }

// @Override
// public void deleteStation(int id) {
// stationRepo.deleteById((long) id);
// }
// }
