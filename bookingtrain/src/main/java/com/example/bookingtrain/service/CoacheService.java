package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Coache;
import com.example.bookingtrain.repository.CoacheRepository;
import com.example.bookingtrain.service.inter.ICoacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoacheService implements ICoacheService {

    private CoacheRepository coacheRepository;

    @Autowired
    public CoacheService(CoacheRepository coacheRepository) {
        this.coacheRepository = coacheRepository;
    }

    public List<Coache> getAll() {
        return coacheRepository.findAll();
    }

    public Coache getById(int id) {
        return coacheRepository.findById(id).orElse(null);
    }

    public Coache save(Coache coache) {
        return coacheRepository.save(coache);
    }

    public Coache update(Coache coache) {
        return coacheRepository.saveAndFlush(coache);
    }

    public boolean delete(int id) {
        Coache coache = coacheRepository.findById(id).orElse(null);
        if (coache != null) {
            coache.setStatusCoache(0);
            return true;
        }
        return false;
    }
}
