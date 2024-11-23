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
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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
        Employee employee = employeeService.findByUserId(user.getUserId());
        if(employee == null) {
            // Chú bé mới đăng nhập thì bắt nhập đu thông tin
            // tại AccountInfo.html
            // nhập thếu nhốt ko cho ra
            employee = new Employee();
        }

        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        return "/Client/Components/AccountInfo";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateAccount/{requestFrom}")
    public String accountInfo(@ModelAttribute("user") User user,
                              @ModelAttribute("employee") Employee employee,
                              @PathVariable("requestFrom") String requestFrom,
                              RedirectAttributes redirectAttributes, Model model) {

        Employee existingEmployee = employeeService.findByUserId(user.getUserId());
        if(existingEmployee == null){
            // Nếu chưa có dữ liệu trên database thì gán tạo mới employee
            // và gán userId
            existingEmployee = new Employee();
            existingEmployee.setUserId(user.getUserId());
        }
        boolean isComeFromFuture = DAYS.between(employee.getDateOfBirth(), LocalDate.now()) <= 0 ? true : false;
        if(isComeFromFuture ){
            // Thanh nin đến từ tương lai
            redirectAttributes.addFlashAttribute("message", "Vui lòng cung cấp ngày sinh phù họp.");
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
            return "redirect:/account/updateAccount";
        }
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());

        User updatedUser = userService.updateUser(user);
        Employee updatedEmployee = employeeService.updateEmployee(existingEmployee);

        model.addAttribute("user", updatedUser);
        model.addAttribute("employee", updatedEmployee);
        redirectAttributes.addFlashAttribute("message","Information updated successfully");
        return requestFrom.equals("1") ? "redirect:/account/admin" : "redirect:/account";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable Integer id, @PathVariable String requestFrom) {
        // admin tu xoa thi sao nhi?
        userService.deleteUser(id);
        return "redirect:/home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/admin")
    public String showAdminAccount(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        Employee employee = employeeService.findByUserId(user.getUserId());
        if(employee == null) {
            // Chú bé mới đăng nhập thì bắt nhập đu thông tin
            // tại AccountInfo.html
            // nhập thếu nhốt ko cho ra
            employee = new Employee();
        }

        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("employee", employee);
        model.addAttribute("roleList", roleList);
        return "/admin/AccountInfoAdmin";
    }

}
