package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Employee;
import com.example.bookingtrain.service.EmployeeService;
import com.example.bookingtrain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "list/employeeList";
    }

    @GetMapping("/newEmployee")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("users", userService.getAllUsers());
        return "add/addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees"; // Điều hướng về trang danh sách nhân viên
    }

    @GetMapping("/editEmployee/{employeeID}")
    public String editEmployeeForm(@PathVariable Integer employeeID, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeID);
        model.addAttribute("employee", employee);
        model.addAttribute("users", userService.getAllUsers());
        return "edit/editEmployee";
    }

    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

}
