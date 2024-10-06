package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TrainService {

    private TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getAll() {
        return trainRepository.findAll();
    }

    public Train getById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No train found with id: " + id));
    }

    public Train create(Train train) {
        return trainRepository.save(train);
    }

    public Train update(Train train, Long id) {
        // Tim train trong CSDL
        Train trainTemp = trainRepository.findById(id)
                .orElse(null);

        // Neu tìm thay: Cap nhat train
        if (trainTemp != null) {
            trainTemp.setTrainCode(train.getTrainCode());
            trainTemp.setTrainName(train.getTrainName());
            trainTemp.setImage(train.getImage());
            trainTemp.setDescription(train.getDescription());
            return trainRepository.save(trainTemp);
        } else throw new EntityNotFoundException("No train found with id: " + id);

    }

    public Train update(Train train) {
        return trainRepository.save(train);
    }

    public void delete(Long id){
        trainRepository.deleteById(id);
    }
}
