package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.service.RoleService;
import com.example.bookingtrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping
    public String getAllUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("userId").ascending());
        Page<User> usersPage;

        if (search != null && !search.isEmpty()) {
            usersPage = userService.searchUsersByUsername(search, pageable);
        } else {
            usersPage = userService.getAllUsers(pageable);
        }

        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("baseUrl", "/users");
        model.addAttribute("search", search);
        return "list/userList";
    }

    @GetMapping("/newUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "add/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable Integer userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit/editUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}