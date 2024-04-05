package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.exception;

import java.util.List;

public class SedeNotFoundException extends RuntimeException {
    private List<String> details;

    public SedeNotFoundException(String message, List<String> details) {
        super(message);
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }
}