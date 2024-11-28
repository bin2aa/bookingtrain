package com.example.bookingtrain.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
            session.setAttribute("oauthProvider", authenticatedUser.getOauthProvider());
            session.setAttribute("oauthId", authenticatedUser.getOauthId());
            session.setAttribute("user", authenticatedUser);
            return "redirect:/home";
        } else {
            model.addAttribute("errorLogin", "Email hoặc mật khẩu không đúng");
            return "login/login";
        }
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User principal, HttpSession session,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        String email = principal.getAttribute("email");
        String provider = client.getClientRegistration().getRegistrationId().toUpperCase();
        String oauthId = principal.getAttribute("sub"); // Đối với Google
        if ("FACEBOOK".equals(provider)) {
            oauthId = principal.getAttribute("id"); // Đối với Facebook
            email = principal.getAttribute("email"); // Lấy email từ Facebook
            System.out.println("Facebook email: " + email); // Thêm dòng log để kiểm tra email
        }

        if (email == null) {
            // Nếu email vẫn null, thử lấy từ các thuộc tính khác
            email = (String) principal.getAttributes().get("email");
            System.out.println("Fallback email: " + email);
        }

        // Thêm các câu lệnh debug để kiểm tra các thuộc tính khác
        System.out.println("OAuth2 Provider: " + provider);
        System.out.println("OAuth2 ID: " + oauthId);

        // Logic hiện tại để xử lý tạo hoặc cập nhật người dùng
        User user = userService.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername(principal.getAttribute("name"));
            user.setPassword("oauth2_" + provider);
            user.setRoleId(1);
            user.setOauthProvider(provider);
            user.setOauthId(oauthId);
            userService.createUser(user);
        } else {
            if (!provider.equals(user.getOauthProvider())) {
                user.setOauthProvider(provider);
                user.setOauthId(oauthId);
                userService.updateUser(user);
            }
        }

        session.setAttribute("username", user.getUsername());
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("role", user.getRole().getRoleName());
        session.setAttribute("oauthProvider", user.getOauthProvider());
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    // @GetMapping("/loginSuccess")
    // public String loginSuccess(@AuthenticationPrincipal OAuth2User principal,
    // HttpSession session) {
    // String email = principal.getAttribute("email");
    // User user = userService.findByEmail(email);
    // if (user == null) {
    // user = new User();
    // user.setEmail(email);
    // user.setUsername(principal.getAttribute("name"));
    // user.setPassword("google_oauth2"); // Set a default password
    // user.setRoleId(1); // Set default role (user role)
    // userService.createUser(user);
    // }
    // session.setAttribute("username", user.getUsername());
    // session.setAttribute("userId", user.getUserId());
    // session.setAttribute("role", user.getRole().getRoleName());
    // return "redirect:/home";
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