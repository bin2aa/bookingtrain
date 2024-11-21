package com.example.bookingtrain.config;

import com.example.bookingtrain.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.bookingtrain.DTO.CustomUserDetails;
import com.example.bookingtrain.model.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .antMatchers("/login", "/register").permitAll()
                        .antMatchers("/stations/client/**").authenticated()
                        .antMatchers("/account/**").authenticated()
                        .antMatchers("/searchTickets/**").permitAll()
                        .antMatchers("/passengers/ticket/passenger/**").permitAll()
                        .antMatchers("/home/**").permitAll()
                        .antMatchers("/seats/all").permitAll()
                        .antMatchers("/checkLoginStatus").permitAll()
                        .antMatchers("/tickets/addPassengers").authenticated() // Yêu cầu phải đăng nhập
                        .antMatchers("/payment/create").permitAll()
                        .antMatchers("/admin/**").hasAuthority("Admin")
                        // .antMatchers("/**").hasAnyAuthority("Admin", "Staff")
                        .antMatchers("/**").access("!hasAuthority('User')")

                )
                // .antMatchers("/**", "/admin/**").hasAuthority("Admin"))
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                            String userName = userDetails.getUser().getUsername();
                            String role = userDetails.getUser().getRole().getRoleName();
                            int userId = userDetails.getUser().getUserId();
                            User user = userDetails.getUser();

                            request.getSession().setAttribute("username", userName);
                            request.getSession().setAttribute("userId", userId);
                            request.getSession().setAttribute("role", role);
                            request.getSession().setAttribute("user", user);

                            // Điều hướng về trang trước đó nếu có
                            String referrer = (String) request.getSession().getAttribute("requestedPage");
                            if (referrer != null) {
                                request.getSession().removeAttribute("requestedPage");
                                response.sendRedirect(referrer);
                            } else {
                                response.sendRedirect("/home");
                            }
                        })
                        .failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("errorLogin", "Email hoặc mật khẩu không đúng");
                            response.sendRedirect("/login");
                        })
                        .permitAll())
                .logout(logout -> logout
                        .permitAll())
                .csrf(csrf -> csrf.disable());
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

// package com.example.bookingtrain.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http
// .authorizeRequests()
// .antMatchers("/**").permitAll()
// .anyRequest().authenticated()
// .and()
// .csrf().disable();
// }
// }