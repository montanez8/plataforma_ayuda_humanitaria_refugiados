package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes;

import java.util.List;

import lombok.Data;

@Data
public class InformeVoluntarioDto {
    private String nombre;
    private String profesion;
    private boolean disponibilidad;
    private List<SedeDto> sedes;
    private int numeroTrabajos;
}
