package com.example.train.Service;


import com.example.train.Model.CarriageTypeModel;
import com.example.train.Repository.CarriageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarriageTypeService {
    @Autowired
    private CarriageTypeRepository carriageTypeRepository;

    public List<CarriageTypeModel> getAllCarriageTypes() {
        return carriageTypeRepository.findAll();
    }
}
