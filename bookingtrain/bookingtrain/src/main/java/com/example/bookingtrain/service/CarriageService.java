package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Carriage;
import com.example.bookingtrain.repository.CarriageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CarriageService {

    @Autowired
    private CarriageRepository carriageRepository;

    public List<Carriage> getAllCarriage() {
        return carriageRepository.findAll();
    }

    public Carriage getCarriageById(Long carriagesIDTest) {
        return carriageRepository.findById(carriagesIDTest).orElse(null);
    }

    public Carriage createCarriage(Carriage cariage) {
        return carriageRepository.save(cariage);
    }

    public Carriage updateCarriage(Carriage carriage) {
        return carriageRepository.saveAndFlush(carriage);
    }

    public void deleteCarriage(Long cariageID) {
        carriageRepository.deleteById(cariageID);
    }

    public Carriage findByCarriageName(String carriageName) {
        return carriageRepository.findByCarriageName(carriageName);
    }

}