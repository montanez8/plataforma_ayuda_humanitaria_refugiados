package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.TipoVoluntario;

import lombok.Data;

@Data
public class InformeVoluntEnvioDto {
    private String nombre;
    private TipoVoluntario tipoVoluntario;
    private String profesion;
}
