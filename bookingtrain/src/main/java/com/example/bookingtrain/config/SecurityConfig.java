package com.example.bookingtrain.config;

import com.example.bookingtrain.DTO.CustomUserDetails;
import com.example.bookingtrain.service.CustomUserDetailsService;
//import net.bytebuddy.asm.MemberSubstitution;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(configurer -> configurer
                        .requestMatchers("/login","/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/**", "/home/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .successHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();

                            Object principal = authentication.getPrincipal();
                            if (principal instanceof CustomUserDetails) {
                                CustomUserDetails authenticatedUser = (CustomUserDetails) principal;

                                session.setAttribute("username", authenticatedUser.getName());
                                session.setAttribute("userId", authenticatedUser.getUserId());
                                session.setAttribute("role", authenticatedUser.getRole());
                            }
//                            session.setAttribute("user", authentication.getPrincipal()); // Lưu thông tin người dùng vào session
                            response.sendRedirect("/home");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Luôn tạo session khi người dùng đăng nhập
                        .maximumSessions(50) // Giới hạn số lượng session tối đa cho mỗi người dùng
                        .maxSessionsPreventsLogin(true) // Ngăn người dùng đăng nhập từ thiết bị khác nếu đã đạt số lượng session tối đa
                );;

        return http.build();
    }


    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .antMatchers("/login", "/register", "/").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .permitAll())
////                .formLogin(wh)
//                .logout(logout -> logout
//                        .permitAll());
//
//        return http.build();
//    }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }
// }
}