package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Acceso denegado: no estás autenticado \"}");
    }
}
