package com.example.bookingtrain.service;

import com.example.bookingtrain.model.CarriageType;
import com.example.bookingtrain.repository.CarriageTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CarriageTypeService {

    @Autowired
    private CarriageTypeRepository carriageTypeRepository;

    public List<CarriageType> getAllCarriageType() {
        return carriageTypeRepository.findAll();
    }

    public CarriageType getCarriageTypeById(Long carriageTypesIDTest) {
        return carriageTypeRepository.findById(carriageTypesIDTest).orElse(null);
    }

    public CarriageType createCarriageType(CarriageType cariage) {
        return carriageTypeRepository.save(cariage);
    }

    public CarriageType updateCarriageType(CarriageType carriageType) {
        return carriageTypeRepository.saveAndFlush(carriageType);
    }

    public void deleteCarriageType(Long cariageID) {
        carriageTypeRepository.deleteById(cariageID);
    }

    public CarriageType findByCarriageTypeName(String carriageTypeName) {
        return carriageTypeRepository.findByCarriageTypeName(carriageTypeName);
    }

}