package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Role;
import com.example.bookingtrain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getAllRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "list/roleList";
    }

    @GetMapping("/newRole")
    public String addRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "add/addRole";
    }

    @PostMapping("/addRole")
    public String addRole(@ModelAttribute Role role) {
        roleService.createRole(role);
        return "redirect:/roles";
        // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/editRole/{roleId}")
    public String editRoleForm(@PathVariable Integer roleId, Model model) { // Đảm bảo kiểu dữ liệu là Long
        Role role = roleService.getRoleById(roleId);
        model.addAttribute("role", role);
        return "edit/roleEdit";
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute Role role) {
        roleService.updateRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/deleteRole/{id}")
    public String deleteRole(@PathVariable Integer id) { // Đảm bảo kiểu dữ liệu là Long
        roleService.deleteRole(id);
        return "redirect:/roles";
    }
}