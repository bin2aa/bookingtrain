package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Operation;
import com.example.bookingtrain.repository.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Operation getOperationById(Integer id) {
        return operationRepository.findById(id).orElse(null);
    }

    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public Operation updateOperation(Operation operation) {
        return operationRepository.saveAndFlush(operation);
    }

    public void deleteOperation(Integer id) {
        operationRepository.deleteById(id);
    }

    public Operation findByOperationName(String fullName) {
        return operationRepository.findByOperationName(fullName);
    }
}
