package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.repository.TrainRepository;
import com.example.bookingtrain.service.inter.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService implements ITrainService {

    private TrainRepository repo;

    @Autowired
    public TrainService(TrainRepository repo) {
        this.repo = repo;
    }

    public Train getByTrainName(String trainName) {
        return repo.findByTrainName(trainName);
    }

    public Train getByTrainCode(String trainCode) {
        return repo.findByTrainCode(trainCode);
    }

    @Override
    public Train getById(Integer id) {
        return repo.findById((long) id).orElse(null);
    }

    @Override
    public List<Train> getAllTrains() {
        return repo.findAll();
    }

    @Override
    public Train save(Train train) {
        return repo.save(train);
    }

    @Override
    public void delete(Integer id) {
        Train train = getById(id);
        train.setStatusTrain(0);
        repo.save(train);
    }
}
