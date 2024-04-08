package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.models;

import lombok.Data;

@Data
public class JWTRequest {

    private String username;
    private String password;
}
