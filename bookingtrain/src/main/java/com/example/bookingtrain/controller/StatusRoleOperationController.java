package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.StatusRoleOperation;
import com.example.bookingtrain.service.StatusRoleOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/statusRoleOperations")
public class StatusRoleOperationController {
    @Autowired
    private StatusRoleOperationService statusRoleOperationService;

    @GetMapping
    public String getAllStatusRoleOperations(Model model) {
        List<StatusRoleOperation> statusRoleOperations = statusRoleOperationService.getAllStatusRoleOperations();
        model.addAttribute("statusRoleOperations", statusRoleOperations);
        return "list/statusRoleOperationList";
    }

    @GetMapping("/newStatusRoleOperation")
    public String addStatusRoleOperationForm(Model model) {
        model.addAttribute("statusRoleOperation", new StatusRoleOperation());
        return "add/addStatusRoleOperation";
    }

    @PostMapping("/addStatusRoleOperation")
    public String addStatusRoleOperation(@ModelAttribute StatusRoleOperation statusRoleOperation) {
        statusRoleOperationService.createStatusRoleOperation(statusRoleOperation);
        return "redirect:/statusRoleOperations";
    }

    @GetMapping("/editStatusRoleOperation/{statusId}")
    public String editStatusRoleOperationForm(@PathVariable Integer statusId, Model model) {
        StatusRoleOperation statusRoleOperation = statusRoleOperationService
                .getStatusRoleOperationById(statusId);
        model.addAttribute("statusRoleOperation", statusRoleOperation);
        return "edit/statusRoleOperationEdit";
    }

    @PostMapping("/edit")
    public String editStatusRoleOperation(@ModelAttribute StatusRoleOperation StatusRoleOperation) {
        statusRoleOperationService.updateStatusRoleOperation(StatusRoleOperation);
        return "redirect:/statusRoleOperations";
    }

    @GetMapping("/deleteStatusRoleOperation/{id}")
    public String deleteStatusRoleOperation(@PathVariable Integer id) {
        statusRoleOperationService.deleteStatusRoleOperation(id);
        return "redirect:/statusRoleOperations";
    }
}