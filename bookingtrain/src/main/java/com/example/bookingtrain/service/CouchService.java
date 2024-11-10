package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Couch;
import com.example.bookingtrain.repository.CouchRepository;
import com.example.bookingtrain.service.inter.ICouchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouchService implements ICouchService {

    private CouchRepository couchRepository;

    @Autowired
    public CouchService(CouchRepository couchRepository) {
        this.couchRepository = couchRepository;
    }

    public List<Couch> getAll() {
        return couchRepository.findAll();
    }

    public Couch getById(int id) {
        return couchRepository.findById(id).orElse(null);
    }

    public Couch save(Couch couch) {
        return couchRepository.save(couch);
    }

    public boolean delete(int id) {
        Couch couch = couchRepository.findById(id).orElse(null);
        if(couch != null) {
            couch.setStatusCouch(0);
            return true;
        }
        return false;
    }
}
