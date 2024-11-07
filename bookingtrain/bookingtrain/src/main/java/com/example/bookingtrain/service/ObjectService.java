package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Object;
import com.example.bookingtrain.repository.ObjectRepository;
import com.example.bookingtrain.service.inter.IObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService implements IObjectService {

    private ObjectRepository repo;

    @Autowired
    public ObjectService(ObjectRepository objectRepository) {
        this.repo = objectRepository;
    }

    public Object getById(Integer id) {
        return repo.findById((long) id).orElse(null);
    }

    public List<Object> getAll() {
        return repo.findAll();
    }

    public Object save(Object object) {
        return repo.save(object);
    }

    public void delete(Integer id) {
        repo.deleteById((long) id);
    }
}
