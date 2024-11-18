package com.example.bookingtrain.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.service.UserService;

import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User()); // Thêm đối tượng User vào mô hình
        return "login/login";
    }

    @GetMapping("/loginHandler")
    @PreAuthorize("isAuthenticated()")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        // if (userService.authenticate(user.getEmail(), user.getPassword())) {
        // return "redirect:/";
        // } else {
        // model.addAttribute("errorLogin", "email hoặc mật khẩu không đúng");
        // return "login/login";
        // }
        if (userService.authenticate(user.getEmail(), user.getPassword())) {
            // session.setAttribute("username", user.getUsername());
            // session.setAttribute("userId", user.getUserId());
            User authenticatedUser = userService.findByEmail(user.getEmail()); // Lấy thông tin user đã được xác thực
            session.setAttribute("username", authenticatedUser.getUsername());
            session.setAttribute("userId", authenticatedUser.getUserId());
            session.setAttribute("role", authenticatedUser.getRole().getRoleName());

            return "redirect:/";
        } else {
            model.addAttribute("errorLogin", "Email hoặc mật khẩu không đúng");
            return "login/login";
        }
    }

    // @PostMapping("/register")
    // public String register(@ModelAttribute("user") User user, Model model) {
    // if (!user.getPassword().equals(user.getRepeatPassword())) {
    // model.addAttribute("errorRegister", "Mật khẩu và mật khẩu xác nhận không
    // khớp");
    // return "login/login";
    // }
    // if (userService.isEmailExisted(user.getEmail())) {
    // model.addAttribute("errorRegister", "Email đã tồn tại");
    // return "login/login";
    // } else {
    // userService.createUser(user);
    // return "redirect:/";
    // }
    // }

    @PostMapping("/register")
    @ResponseBody
    public String register(@ModelAttribute("user") User user, Model model) {
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            return "PasswordNotMatch";
        }
        if (userService.isEmailExisted(user.getEmail())) {
            return "EmailExisted";
        } else {
            userService.createUser(user);
            return "Success";
        }
    }
}