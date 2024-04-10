package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MaterialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int cantidad;
}
