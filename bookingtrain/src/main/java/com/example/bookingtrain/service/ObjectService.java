package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Object;
import com.example.bookingtrain.repository.ObjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ObjectService {
    @Autowired
    private ObjectRepository objectRepository;

    public List<Object> getAllObjects() {
        return objectRepository.findAll();
    }

    public Object getObjectById(Integer id) {
        return objectRepository.findById(id).orElse(null);
    }

    public Object createObject(Object object) {
        return objectRepository.save(object);
    }

    public Object updateObject(Object object) {
        return objectRepository.saveAndFlush(object);
    }

    public void deleteObject(Integer id) {
        objectRepository.deleteById(id);
    }

    public Object getDefaultObjectById(Integer id) {
        return getObjectById(id);
    }

    public void initializeDefaultObjects() {
        if (objectRepository.findById(1).isEmpty()) {
            objectRepository.save(new Object(1, "Trẻ Em", 50000));
        }
        if (objectRepository.findById(2).isEmpty()) {
            objectRepository.save(new Object(2, "Người Lớn", 100000));
        }
        if (objectRepository.findById(3).isEmpty()) {
            objectRepository.save(new Object(3, "Người Già", 70000));
        }
    }

}
