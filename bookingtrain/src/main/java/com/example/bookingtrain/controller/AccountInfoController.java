package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Employee;
import com.example.bookingtrain.model.Role;
import com.example.bookingtrain.service.EmployeeService;
import com.example.bookingtrain.service.RoleService;
import com.example.bookingtrain.service.UserService;
import com.example.bookingtrain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountInfoController {

    private UserService userService;
    private EmployeeService employeeService;
    private RoleService roleService;

    @Autowired
    public AccountInfoController(UserService userService, EmployeeService employeeService, RoleService roleService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.roleService = roleService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String accountInfo(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        Integer userId = user.getUserId();
        Employee employee = new Employee();
        if(userId != null) employee = employeeService.findByUserId(userId);
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        return "/Client/Components/AccountInfo";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateAccount")
    public String accountInfo(@ModelAttribute("user") User user,
                              @ModelAttribute("employee") Employee employee,
                              RedirectAttributes redirectAttributes, Model model) {
//        User existingUser = userService.getUserById(user.getUserId());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(user.getPassword());
//        existingUser.setUsername(user.getUsername());

        Employee existingEmployee = employeeService.findByUserId(user.getUserId());
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());

        User updatedUser = userService.updateUser(user.getUserId(), user);
        Employee updatedEmployee = employeeService.updateEmployee(existingEmployee);

        model.addAttribute("user", updatedUser);
        model.addAttribute("employee", updatedEmployee);
        redirectAttributes.addFlashAttribute("message","Information updated successfully");
        return "redirect:/account";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/home";
    }
}
