package com.example.config;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .build();
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("Login successfully !")));
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401, exception.getMessage())));
    }

}
