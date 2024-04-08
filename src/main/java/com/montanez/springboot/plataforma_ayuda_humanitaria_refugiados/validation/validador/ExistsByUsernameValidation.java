package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.UsuarioService;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.annotation.ExistsByUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements
        ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UsuarioService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (service == null) {
            return true;
        }
        return !service.existsByUsername(username);
    }

}