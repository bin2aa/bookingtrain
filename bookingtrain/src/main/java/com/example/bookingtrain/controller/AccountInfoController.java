package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Employee;
import com.example.bookingtrain.service.EmployeeService;
import com.example.bookingtrain.service.UserService;
import com.example.bookingtrain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountInfoController {

    private UserService userService;
    private EmployeeService employeeService;

    @Autowired
    public AccountInfoController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String accountInfo(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        return "/Client/Components/AccountInfo";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editAccount")
    public String accountInfo(@ModelAttribute("user") User user, Model model) {
        User existingUser = userService.getUserById(user.getUserId());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUsername(user.getUsername());

        Employee existingEmployee = employeeService.findByUserId(user.getUserId());
//        existingEmployee.setEmployeeName()

        User updatedUser = userService.updateUser(existingUser);

        model.addAttribute("user", updatedUser);
        return "/Client/Components/AccountInfo";
    }
}
