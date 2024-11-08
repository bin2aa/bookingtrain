package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.service.RoleService;
import com.example.bookingtrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping // Xử lý yêu cầu GET tới đường dẫn /users
    public String getAllUsers(Model model) { // Model là một đối tượng dùng để truyền dữ liệu từ Controller tới View
        List<User> users = userService.getAllUsers(); // Lấy danh sách users từ service
        model.addAttribute("users", users); // Thêm danh sách users vào model
        return "list/userList"; // Trả về template userList.html
    }

    @GetMapping("/newUser") // Xử lý yêu cầu GET tới đường dẫn /users/addUser
    public String addUserForm(Model model) {
        model.addAttribute("user", new User()); // Thêm một user mới vào model
        model.addAttribute("roles", roleService.getAllRoles());
        return "add/addUser"; // Trả về template addUser.html
    }

    @PostMapping("/addUser") // Xử lý yêu cầu POST tới đường dẫn /users/addUser
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users"; // Sau khi thêm user xong, chuyển hướng về trang danh sách user
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