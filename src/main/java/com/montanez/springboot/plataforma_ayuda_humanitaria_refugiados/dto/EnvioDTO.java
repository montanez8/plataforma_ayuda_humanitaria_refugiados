package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class EnvioDTO {
    private Long id;
    private String codigo;
    private String destino;
    private LocalDate fechaEnvio;
    private List<Long> sedesIds;
    private List<Long> voluntariosIds;
}