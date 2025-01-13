package com.website.vcb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/hello").permitAll() // Cho phép truy cập công khai tới "/hello"
                        .anyRequest().authenticated() // Yêu cầu xác thực với các đường dẫn khác
                )
                .formLogin(form -> form.defaultSuccessUrl("/hello", true)) // Cấu hình form login
                .logout(logout -> logout.permitAll()); // Bật tính năng logout
        return http.build();
    }
}
