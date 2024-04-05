package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private List<String> details;
}