package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Operation;
import com.example.bookingtrain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping
    public String getAllOperations(Model model) {
        List<Operation> operations = operationService.getAllOperations();
        model.addAttribute("operations", operations);
        return "list/operationList";
    }

    @GetMapping("/newOperation")
    public String addOperationForm(Model model) {
        model.addAttribute("operation", new Operation());
        return "add/addOperation";
    }

    @PostMapping("/addOperation")
    public String addOperation(@ModelAttribute Operation operation) {
        operationService.createOperation(operation);
        return "redirect:/operations";
    }

    @GetMapping("/editOperation/{operationId}")
    public String editOperationForm(@PathVariable Integer operationId, Model model) { // Đảm bảo kiểu dữ liệu là Long
        Operation operation = operationService.getOperationById(operationId);
        model.addAttribute("operation", operation);
        return "edit/editOperation";
    }

    @PostMapping("/edit")
    public String editOperation(@ModelAttribute Operation Operation) {
        operationService.updateOperation(Operation);
        return "redirect:/operations";
    }

    @GetMapping("/deleteOperation/{id}")
    public String deleteOperation(@PathVariable Integer id) { // Đảm bảo kiểu dữ liệu là Long
        operationService.deleteOperation(id);
        return "redirect:/operations";
    }
}