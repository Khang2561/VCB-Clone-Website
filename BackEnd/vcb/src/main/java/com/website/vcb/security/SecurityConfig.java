package com.website.vcb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
/*
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Tắt CSRF trong môi trường phát triển
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/users").permitAll() // Cho phép truy cập công khai tới /users
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.defaultSuccessUrl("/hello", true))
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}*/

