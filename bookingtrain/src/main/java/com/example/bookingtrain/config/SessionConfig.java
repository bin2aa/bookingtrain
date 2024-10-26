package com.example.bookingtrain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
public class SessionConfig {

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new CookieHttpSessionIdResolver();
        // return HeaderHttpSessionIdResolver.xAuthToken();
    }
}