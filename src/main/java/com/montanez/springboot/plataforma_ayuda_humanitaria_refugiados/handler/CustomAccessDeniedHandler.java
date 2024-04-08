package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, jakarta.servlet.ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream()
                .println("{ \"error\": \"Acceso denegado: " + accessDeniedException.getMessage() + "\" }");

    }

}