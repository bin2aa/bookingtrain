package com.example.bookingtrain.service;

import com.example.bookingtrain.model.StatusRoleOperation;
import com.example.bookingtrain.repository.StatusRoleOperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class StatusRoleOperationService {
    @Autowired
    private StatusRoleOperationRepository statusRoleOperationRepository;

    public List<StatusRoleOperation> getAllStatusRoleOperations() {
        return statusRoleOperationRepository.findAll();
    }

    public StatusRoleOperation getStatusRoleOperationById(Integer id) {
        return statusRoleOperationRepository.findById(id).orElse(null);
    }

    public StatusRoleOperation createStatusRoleOperation(StatusRoleOperation statusroleoperation) {
        return statusRoleOperationRepository.save(statusroleoperation);
    }

    public StatusRoleOperation updateStatusRoleOperation(StatusRoleOperation statusroleoperation) {
        return statusRoleOperationRepository.saveAndFlush(statusroleoperation);
    }

    public void deleteStatusRoleOperation(Integer id) {
        statusRoleOperationRepository.deleteById(id);
    }

    public StatusRoleOperation findByStatusRoleOperationName(String statusName) {
        return statusRoleOperationRepository.findByStatusName(statusName);
    }
}
