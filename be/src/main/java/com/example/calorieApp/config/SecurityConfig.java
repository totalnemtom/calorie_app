package com.example.calorieApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())  // Permit all requests without authentication
                .csrf().disable()  // Optional: Disable CSRF protection if not needed
                .formLogin().disable()  // Disable form login
                .httpBasic().disable();  // Disable HTTP Basic authentication

        return http.build();
    }
}
