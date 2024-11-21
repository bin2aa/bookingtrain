// package com.example.bookingtrain.config;

// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.web.access.AccessDeniedHandler;
// import org.springframework.stereotype.Component;

// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;

// @Component
// public class CustomAccessDeniedHandler implements AccessDeniedHandler {

//     @Override
//     public void handle(HttpServletRequest request, HttpServletResponse response,
//             AccessDeniedException accessDeniedException) throws IOException, ServletException {
//         // Kiểm tra nếu người dùng chưa đăng nhập
//         if (request.getUserPrincipal() == null) {
//             request.getSession().setAttribute("errorLogin", "Bạn cần đăng nhập để truy cập trang này.");
//             response.sendRedirect("/login");
//         } else {
//             // Xử lý các trường hợp khác nếu cần
//             response.sendRedirect("/access-denied");
//         }
//     }
// }