package com.example.bookingtrain.service;

import com.example.bookingtrain.model.RoleOperation;
import com.example.bookingtrain.model.RoleOperationId;
import com.example.bookingtrain.repository.RoleOperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class RoleOperationService {
    @Autowired
    private RoleOperationRepository roleOperationRepository;

    public List<RoleOperation> getAllRoleOperations() {
        return roleOperationRepository.findAll();
    }

    public RoleOperation getRoleOperationById(RoleOperationId id) {
        return roleOperationRepository.findById(id).orElse(null);
    }

    public RoleOperation createRoleOperation(RoleOperation roleoperation) {
        return roleOperationRepository.save(roleoperation);
    }

    public RoleOperation updateRoleOperation(RoleOperation roleoperation) {
        return roleOperationRepository.saveAndFlush(roleoperation);
    }

    public void deleteRoleOperation(RoleOperationId id) {
        roleOperationRepository.deleteById(id);
    }
}
