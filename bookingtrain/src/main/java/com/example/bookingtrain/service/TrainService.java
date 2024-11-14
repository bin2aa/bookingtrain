package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.repository.TrainRepository;
import com.example.bookingtrain.service.inter.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService implements ITrainService {

    private final TrainRepository repo;

    @Autowired
    public TrainService(TrainRepository repo) {
        this.repo = repo;
    }

    public Train getByTrainName(String trainName) {
        return repo.findByTrainName(trainName);
    }

    @Override
    public Train getById(Integer id) {
        return repo.findById(id).orElse(null);
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
    public Train update(Integer id, Train updatedTrain) {
        Train existingTrain = getById(id);
        if (existingTrain != null) {
            existingTrain.setTrainName(updatedTrain.getTrainName());
            existingTrain.setDescription(updatedTrain.getDescription());
            existingTrain.setStatusTrain(updatedTrain.getStatusTrain());
            existingTrain.setImage(updatedTrain.getImage());
            return repo.saveAndFlush(existingTrain);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Train train = getById(id);
        if (train != null) {
            train.setStatusTrain(0);
            repo.save(train);
            return true;
        }
        return false;
    }
}
