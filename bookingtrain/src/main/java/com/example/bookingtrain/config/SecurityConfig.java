package com.example.bookingtrain.config;

import com.example.bookingtrain.DTO.CustomUserDetails;
import com.example.bookingtrain.model.User;
import com.example.bookingtrain.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/img/**").permitAll()
                .antMatchers("/stations/client/**").authenticated() // Ai dang nhập thi được truy cập
                .antMatchers("/account/**").authenticated()
                .antMatchers("/home/**").permitAll() // Public "/home"
                .antMatchers("/**","/admin/**").hasRole("ADMIN") // Admin truy cập đưọc "/" , "/admin"
                .and() // Neu chua dang nhập mà truy cap trang admin thi show form login
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler((request, response, authentication) -> {
                        CustomUserDetails userDetails= (CustomUserDetails) authentication.getPrincipal();
                        String userName = userDetails.getUser().getUsername();
                        String role = userDetails.getUser().getRole().getRoleName();
                        int userId = userDetails.getUser().getUserId();
                        User user = userDetails.getUser();

                        request.getSession().setAttribute("username", userName);
                        request.getSession().setAttribute("userId", userId);
                        request.getSession().setAttribute("role", role);
                        request.getSession().setAttribute("user", user);
                        response.sendRedirect("/home");
                    })
//                    .defaultSuccessUrl("/home", true)
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
//                .anyRequest().authenticated()
                .and()
                .csrf().disable(); // Tắt CSRF để đơn giản hóa cấu hình
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}