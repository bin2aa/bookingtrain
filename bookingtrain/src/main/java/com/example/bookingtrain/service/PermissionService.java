package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Permission;
import com.example.bookingtrain.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission getPermissionById(Integer id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Permission permission) {
        return permissionRepository.saveAndFlush(permission);
    }

    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }

}
