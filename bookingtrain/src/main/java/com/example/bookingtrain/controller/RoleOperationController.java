package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.RoleOperation;
import com.example.bookingtrain.model.RoleOperationId;
import com.example.bookingtrain.service.OperationService;
import com.example.bookingtrain.service.PermissionService;
import com.example.bookingtrain.service.RoleOperationService;
import com.example.bookingtrain.service.RoleService;
import com.example.bookingtrain.service.StatusRoleOperationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public String getAllRoleOperations(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 3; // Số lượng mục trên mỗi trang
        Pageable pageable = PageRequest.of(page, pageSize,
                Sort.by("id.roleId").ascending().and(Sort.by("id.permissionId")).and(Sort.by("id.operationId")));
        Page<RoleOperation> roleOperationsPage = roleOperationService.getAllRoleOperations(pageable);
        model.addAttribute("roleOperations", roleOperationsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", roleOperationsPage.getTotalPages());
        model.addAttribute("baseUrl", "/roleOperations");
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
    public String addRoleOperation(@ModelAttribute RoleOperation roleOperation, Model model) {

        roleOperation.setRole(roleService.getRoleById(roleOperation.getId().getRoleId()));
        roleOperation.setPermission(permissionService.getPermissionById(roleOperation.getId().getPermissionId()));
        roleOperation.setOperation(operationService.getOperationById(roleOperation.getId().getOperationId()));
        roleOperation.setStatusRoleOperation(
                statusRoleOperationService.getStatusRoleOperationById(roleOperation.getStatusId()));

        roleOperationService.createRoleOperation(roleOperation);
        return "redirect:/roleOperations";
    }

    @GetMapping("/editRoleOperation/{roleId}-{permissionId}-{operationId}")
    public String editRoleOperationForm(@PathVariable Integer roleId, @PathVariable Integer permissionId,
            @PathVariable Integer operationId, Model model) {
        RoleOperationId roleOperationId = new RoleOperationId(roleId, permissionId, operationId);
        RoleOperation roleOperation = roleOperationService.getRoleOperationById(roleOperationId);
        model.addAttribute("roleOperation", roleOperation);
        model.addAttribute("statusRoleOperations", statusRoleOperationService.getAllStatusRoleOperations());
        return "edit/editRoleOperation";
    }

    @PostMapping("/edit")
    public String editRoleOperation(@ModelAttribute RoleOperation roleOperation) {
        roleOperationService.updateRoleOperation(roleOperation);

        roleOperation.setRole(roleService.getRoleById(roleOperation.getId().getRoleId()));
        roleOperation.setPermission(permissionService.getPermissionById(roleOperation.getId().getPermissionId()));
        roleOperation.setOperation(operationService.getOperationById(roleOperation.getId().getOperationId()));
        roleOperation.setStatusRoleOperation(
                statusRoleOperationService.getStatusRoleOperationById(roleOperation.getStatusId()));
        return "redirect:/roleOperations";
    }

    @PostMapping("/editJson")
    @ResponseBody
    public String editRoleOperation(@RequestBody Map<String, Integer> payload) {
        Integer roleId = payload.get("roleId");
        Integer permissionId = payload.get("permissionId");
        Integer operationId = payload.get("operationId");
        Integer statusId = payload.get("statusId");

        RoleOperationId roleOperationId = new RoleOperationId(roleId, permissionId, operationId);
        RoleOperation roleOperation = roleOperationService.getRoleOperationById(roleOperationId);
        if (roleOperation == null) {
            return "RoleOperation not found";
        }
        roleOperation.setStatusId(statusId);
        roleOperation.setRole(roleService.getRoleById(roleId));
        roleOperation.setPermission(permissionService.getPermissionById(permissionId));
        roleOperation.setOperation(operationService.getOperationById(operationId));
        roleOperation.setStatusRoleOperation(statusRoleOperationService.getStatusRoleOperationById(statusId));
        roleOperationService.updateRoleOperation(roleOperation);
        return "susscess";
    }

    @GetMapping("/deleteRoleOperation/{roleId}-{permissionId}-{operationId}")
    public String deleteRoleOperation(@PathVariable Integer roleId, @PathVariable Integer permissionId,
            @PathVariable Integer operationId) {
        RoleOperationId roleOperationId = new RoleOperationId(roleId, permissionId, operationId);
        roleOperationService.deleteRoleOperation(roleOperationId);
        return "redirect:/roleOperations";
    }

}