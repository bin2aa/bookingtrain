package com.example.bookingtrain.service;

import com.example.bookingtrain.DTO.CustomUserDetails;
import com.example.bookingtrain.model.RoleOperation;
import com.example.bookingtrain.model.RoleOperationId;
import com.example.bookingtrain.repository.RoleOperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class RoleOperationService {
    @Autowired
    private RoleOperationRepository roleOperationRepository;

    // public List<RoleOperation> getAllRoleOperations() {
    // return roleOperationRepository.findAllOrderByRoleOperationId();
    // }

    public Page<RoleOperation> getAllRoleOperations(Pageable pageable) {
        return roleOperationRepository.findAllOrderByRoleOperationId(pageable);
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

    public boolean hasPermission(Authentication authentication, String permissionName, int operationId) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int roleId = userDetails.getUser().getRoleId();
        String roleName = userDetails.getUser().getRole().getRoleName();

        // Nếu là Admin thì luôn cho phép truy cập
        if ("Admin".equals(roleName)) {
            return true;
        }

        RoleOperation roleOperation = roleOperationRepository.findByRoleIdAndPermissionNameAndOperationId(roleId,
                permissionName, operationId);
        return roleOperation != null && roleOperation.getStatusId() == 1;
    }

}
