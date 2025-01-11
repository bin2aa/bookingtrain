package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.service.RoleService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'users', 1)")
    @GetMapping
    public String getAllUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String roleName,
            Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("userId").ascending());
        Page<User> usersPage;

        if (roleName != null && !roleName.isEmpty()) {
            usersPage = userService.searchUsersByRoleName(roleName, pageable);
        } else if (search != null && !search.isEmpty()) {
            usersPage = userService.searchUsersByUsername(search, pageable);
        } else {
            usersPage = userService.getAllUsers(pageable);
        }

        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("baseUrl", "/users");
        model.addAttribute("search", search);
        model.addAttribute("roleName", roleName);
        model.addAttribute("roles", roleService.getAllRoles());
        return "list/userList";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'users', 2)")
    @GetMapping("/newUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "add/addUser";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'users', 2)")
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'users', 4)")
    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable Integer userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit/editUser";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'users', 4)")
    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'users', 3)")
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}