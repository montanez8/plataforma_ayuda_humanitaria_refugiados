package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.exception;

public class SedeNotFoundException extends RuntimeException {
    public SedeNotFoundException(String message) {
        super(message);
    }
}
