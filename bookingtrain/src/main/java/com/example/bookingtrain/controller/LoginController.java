package com.example.bookingtrain.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookingtrain.model.User;
import com.example.bookingtrain.service.UserService;

import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        model.addAttribute("user", new User());
        String errorLogin = (String) session.getAttribute("errorLogin");
        if (errorLogin != null) {
            model.addAttribute("errorLogin", errorLogin);
            session.removeAttribute("errorLogin");
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        if (userService.authenticate(user.getEmail(), user.getPassword())) {
            User authenticatedUser = userService.findByEmail(user.getEmail());
            session.setAttribute("username", authenticatedUser.getUsername());
            session.setAttribute("userId", authenticatedUser.getUserId());
            session.setAttribute("role", authenticatedUser.getRole().getRoleName());

            return "redirect:/home";
        } else {
            model.addAttribute("errorLogin", "Email hoặc mật khẩu không đúng");
            return "login/login";
        }
    }

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

    @GetMapping("/checkLoginStatus")
    @ResponseBody
    public boolean checkLoginStatus(HttpSession session) {
        return session.getAttribute("username") != null;
    }

    @GetMapping("/getUserId")
    @ResponseBody
    public Integer getUserId(HttpSession session) {
        return (Integer) session.getAttribute("userId");
    }

}