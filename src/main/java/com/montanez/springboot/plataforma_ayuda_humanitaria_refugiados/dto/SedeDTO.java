package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SedeDTO {
    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotEmpty(message = "El domicilio no puede estar vacío")
    private String domicilio;
    @NotEmpty(message = "El director no puede estar vacío")
    private String director;
}
