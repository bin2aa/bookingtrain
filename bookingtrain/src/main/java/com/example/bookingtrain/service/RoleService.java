package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Role;
import com.example.bookingtrain.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

}
