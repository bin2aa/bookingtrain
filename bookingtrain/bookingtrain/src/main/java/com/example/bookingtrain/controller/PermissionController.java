package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Permission;
import com.example.bookingtrain.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String getAllPermissions(Model model) {
        List<Permission> permissions = permissionService.getAllPermissions();
        model.addAttribute("permissions", permissions);
        return "list/permissionList";
    }

    @GetMapping("/newPermission")
    public String addPermissionForm(Model model) {
        model.addAttribute("permission", new Permission());
        return "add/addPermission";
    }

    @PostMapping("/addPermission")
    public String addPermission(@ModelAttribute Permission permission) {
        permissionService.createPermission(permission);
        return "redirect:/permissions";
    }

    @GetMapping("/editPermission/{permissionId}")
    public String editPermissionForm(@PathVariable Integer permissionId, Model model) {
        Permission permission = permissionService.getPermissionById(permissionId);
        model.addAttribute("permission", permission);
        return "edit/editPermission";
    }

    @PostMapping("/edit")
    public String editPermission(@ModelAttribute Permission permission) {
        permissionService.updatePermission(permission);
        return "redirect:/permissions";
    }

    @GetMapping("/deletePermission/{id}")
    public String deletePermission(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return "redirect:/permissions";
    }
}