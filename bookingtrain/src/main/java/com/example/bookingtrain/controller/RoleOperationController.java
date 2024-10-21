package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.RoleOperation;
import com.example.bookingtrain.model.RoleOperationId;
import com.example.bookingtrain.service.OperationService;
import com.example.bookingtrain.service.PermissionService;
import com.example.bookingtrain.service.RoleOperationService;
import com.example.bookingtrain.service.RoleService;
import com.example.bookingtrain.service.StatusRoleOperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roleOperations")
public class RoleOperationController {

    @Autowired
    private RoleOperationService roleOperationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StatusRoleOperationService statusRoleOperationService;

    @Autowired
    private OperationService operationService;

    @GetMapping
    public String getAllRoleOperations(Model model) {
        List<RoleOperation> roleOperations = roleOperationService.getAllRoleOperations();
        model.addAttribute("roleOperations", roleOperations);
        return "list/roleOperationList";
    }

    @GetMapping("/newRoleOperation")
    public String addRoleOperationForm(Model model) {
        model.addAttribute("roleOperation", new RoleOperation());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("permissions", permissionService.getAllPermissions());
        model.addAttribute("operations", operationService.getAllOperations());
        model.addAttribute("statusRoleOperations", statusRoleOperationService.getAllStatusRoleOperations());
        return "add/addRoleOperation";
    }

    @PostMapping("/addRoleOperation")
    public String addRoleOperation(@ModelAttribute RoleOperation roleOperation) {
        roleOperationService.createRoleOperation(roleOperation);

        return "redirect:/roleOperations";
    }

    @GetMapping("/editRoleOperation/{roleId}")
    public String editRoleOperationForm(@PathVariable RoleOperationId roleId, Model model) {
        RoleOperation roleOperation = roleOperationService.getRoleOperationById(roleId);
        model.addAttribute("roleOperation", roleOperation);
        return "edit/roleOperationList";
    }

    @PostMapping("/edit")
    public String editRoleOperation(@ModelAttribute RoleOperation roleOperation) {
        roleOperationService.updateRoleOperation(roleOperation);
        return "redirect:/roleOperations";
    }

    // @GetMapping("/deleteRoleOperation/{id}")
    // public String deleteRoleOperation(@PathVariable Integer id) { // Đảm bảo kiểu
    // dữ liệu là Long
    // roleOperationService.deleteRoleOperation(id);
    // return "redirect:/roleOperations";
    // }
}