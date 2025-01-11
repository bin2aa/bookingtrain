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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        User user = (User) session.getAttribute("user");
        Employee employee = employeeService.findByUserId(user.getUserId());

        // Create new empty Employee if not exists
        if (employee == null) {
            employee = new Employee();
            employee.setUserId(user.getUserId());
        }

        model.addAttribute("user", user);
        model.addAttribute("employee", employee);
        return "Client/Components/AccountInfo";
    }

    // AccountInfoController.java
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editAccount")
    public String editAccount(@ModelAttribute("user") User user,
            @RequestParam("currentPassword") String currentPassword,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Update username
        User existingUser = userService.getUserById(user.getUserId());
        existingUser.setUsername(user.getUsername());
        userService.updateUserProfile(existingUser);

        // Handle password update if provided
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            // Validate password confirmation
            if (!user.getPassword().equals(user.getRepeatPassword())) {
                redirectAttributes.addFlashAttribute("error", "New passwords don't match");
                return "redirect:/account";
            }

            // Update password
            boolean passwordUpdated = userService.updatePassword(
                    user.getUserId(),
                    currentPassword,
                    user.getPassword());

            if (!passwordUpdated) {
                redirectAttributes.addFlashAttribute("error", "Current password is incorrect");
                return "redirect:/account";
            }
        }

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        return "redirect:/account";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editEmployee")
    public String editEmployee(@ModelAttribute("employee") Employee employee, Model model,
            RedirectAttributes redirectAttributes) {
        Employee existingEmployee = employeeService.findByUserId(employee.getUserId());

        if (existingEmployee == null) {
            existingEmployee = new Employee();
            existingEmployee.setUserId(employee.getUserId());
        }

        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setPhone(employee.getPhone());
        employeeService.updateEmployee(existingEmployee);
        model.addAttribute("employee", existingEmployee);

        redirectAttributes.addFlashAttribute("success", "Employee information updated successfully");
        return "redirect:/account";
    }
}