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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/home/**", "/css/**", "/js/**", "/img/**", "/oauth2/**").permitAll()

                        .antMatchers("/login", "/register", "/loginSuccess").permitAll()

                        .antMatchers("/stations/client/**").authenticated()
                        .antMatchers("/account/**").authenticated()
                        .antMatchers("/searchTickets/**", "/home/**").permitAll()
                        .antMatchers("/seats/all").permitAll()
                        .antMatchers("/checkLoginStatus").permitAll()
                        .antMatchers("/tickets/addPassengers").authenticated()
                        .antMatchers("/payment/create", "/payment/session/saveSeatId").permitAll()
                        .antMatchers("/objects/price/**").permitAll()
                        .antMatchers("/admin/**").hasAuthority("Admin")
                        .antMatchers("/**").access("isAuthenticated() and !hasAuthority('User')"))
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