package com.example.bookingtrain.config;

import com.example.bookingtrain.DTO.CustomUserDetails;
import com.example.bookingtrain.model.User;
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
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/home/**", "/css/**", "/js/**", "/img/**", "/logo/**", "/oauth2/**").permitAll()
                        .antMatchers("/stations/client/stations").permitAll()
                        .antMatchers("/passengers/ticket/passenger").permitAll()
                        .antMatchers("/stations/client/stations/map/**").permitAll()
                        // .antMatchers("/passengers/ticket/passenger").authenticated()
                        .antMatchers("/login", "/register", "/loginSuccess").permitAll()

                        .antMatchers("/stations/client/**").authenticated()
                        .antMatchers("/bookings/details/**").authenticated()
                        .antMatchers("/bookings/myBookings").permitAll()
                        .antMatchers("/forgot-password").permitAll()
                        .antMatchers("/reset-password").permitAll()
                        .antMatchers("/account/**").authenticated()
                        .antMatchers("/searchTickets/**", "/home/**").permitAll()
                        .antMatchers("/seats/all").permitAll()
                        .antMatchers("/checkLoginStatus").permitAll()
                        .antMatchers("/tickets/addPassengers", "/account/editEmployee").authenticated()
                        .antMatchers("/payment/create", "/payment/session/saveSeatId").permitAll()
                        .antMatchers("/objects/price/**").permitAll()
                        .antMatchers("/payment/vn-pay", "/payment/vn-pay-callback",
                                "/payment/session/saveTotalPriceAndPassengerData")
                        .authenticated()
                        .antMatchers("/admin/**").hasAuthority("Admin")
                        .antMatchers("/roles/**", "/roleOperations/**", "/permissions/**", "/statusRoleOperations/**")
                        .hasAuthority("Admin")
                        .antMatchers("/**").access("isAuthenticated() and !hasAuthority('User')")

                        // ===============================================

                        .antMatchers("/users/**")
                        .access("@roleOperationService.hasPermission(authentication, 'users', 1)")

                        .antMatchers("/bookings/**")
                        .access("@roleOperationService.hasPermission(authentication, 'bookings', 1)")

                        .antMatchers("/trains/**")
                        .access("@roleOperationService.hasPermission(authentication, 'trains', 1)")

                        .antMatchers("/coaches/**")
                        .access("@roleOperationService.hasPermission(authentication, 'coaches', 1)")

                        .antMatchers("/employees/**")
                        .access("@roleOperationService.hasPermission(authentication, 'employees', 1)")

                        .antMatchers("/objects/**")
                        .access("@roleOperationService.hasPermission(authentication, 'objects', 1)")

                        .antMatchers("/passengers/**")
                        .access("@roleOperationService.hasPermission(authentication, 'passengers', 1)")

                        .antMatchers("/routes/**")
                        .access("@roleOperationService.hasPermission(authentication, 'routes', 1)")

                        .antMatchers("/schedules/**")
                        .access("@roleOperationService.hasPermission(authentication, 'schedules', 1)")

                        .antMatchers("/seats/**")
                        .access("@roleOperationService.hasPermission(authentication, 'seats', 1)")

                        .antMatchers("/seattypes/**")
                        .access("@roleOperationService.hasPermission(authentication, 'seattypes', 1)")

                        .antMatchers("/stations/**")
                        .access("@roleOperationService.hasPermission(authentication, 'stations', 1)")

                        .antMatchers("/tickets/**")
                        .access("@roleOperationService.hasPermission(authentication, 'tickets', 1)"))
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
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        // .defaultSuccessUrl("/home")
                        // .failureUrl("/login?error=true");
                        .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                                .authorizationRequestResolver(
                                        new CustomAuthorizationRequestResolver(
                                                clientRegistrationRepository,
                                                "/oauth2/authorization")))
                        .defaultSuccessUrl("/loginSuccess", true))
                .logout(logout -> logout.permitAll())
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

    private static class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {
        private final OAuth2AuthorizationRequestResolver defaultResolver;

        public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo,
                String authorizationRequestBaseUri) {
            this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, authorizationRequestBaseUri);
        }

        @Override
        public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
            OAuth2AuthorizationRequest req = defaultResolver.resolve(request);
            if (req != null) {
                req = customizeAuthorizationRequest(req);
            }
            return req;
        }

        @Override
        public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
            OAuth2AuthorizationRequest req = defaultResolver.resolve(request, clientRegistrationId);
            if (req != null) {
                req = customizeAuthorizationRequest(req);
            }
            return req;
        }

        private OAuth2AuthorizationRequest customizeAuthorizationRequest(OAuth2AuthorizationRequest req) {
            Map<String, Object> additionalParameters = new HashMap<>();
            additionalParameters.putAll(req.getAdditionalParameters());
            additionalParameters.put("prompt", "select_account");

            return OAuth2AuthorizationRequest.from(req)
                    .additionalParameters(additionalParameters)
                    .build();
        }
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