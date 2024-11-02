package com.example.bookingtrain.service.inter;

import com.example.bookingtrain.model.Train;

import java.util.List;

public interface ITrainService {

    Train getById(Integer id);
    List<Train> getAllTrains();
    Train save(Train train);
    void delete(Integer id);
}
