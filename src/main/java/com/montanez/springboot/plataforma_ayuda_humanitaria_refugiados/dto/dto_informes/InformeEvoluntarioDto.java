package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class InformeEvoluntarioDto {
    private String codigo;
    private String destino;
    private LocalDate fechaEnvio;
    private int numeroVoluntarios;
    private List<InformeVoluntEnvioDto> voluntarios;
}
