package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Employee;
import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.EmployeeService;
import com.example.bookingtrain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'employees', 1)")
    @GetMapping
    public String getAllEmployees(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("employeeId").ascending());
        Page<Employee> employeePage = employeeService.getAllEmployees(pageable);

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("baseUrl", "/employees");
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
