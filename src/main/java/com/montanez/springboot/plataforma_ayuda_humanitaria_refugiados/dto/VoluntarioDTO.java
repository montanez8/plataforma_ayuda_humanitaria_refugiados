package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import java.util.List;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.TipoVoluntario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoluntarioDTO {
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;
    private TipoVoluntario tipoVoluntario;
    @NotNull(message = "La disponibilidad no puede estar vacía")
    private String profesion;
    private boolean disponibilidad;
    @NotEmpty(message = "El número de trabajos no puede estar vacío")
    private int numeroTrabajos;

    private List<Long> sedes;

}
