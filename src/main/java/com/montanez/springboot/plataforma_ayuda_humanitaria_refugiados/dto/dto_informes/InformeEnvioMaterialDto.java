package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes;

import java.time.LocalDate;
import java.util.List;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.MaterialDTO;

import lombok.Data;

@Data
public class InformeEnvioMaterialDto {
    private String destino;
    private LocalDate fechaEnvio;
    private List<MaterialDTO> materiales;
}
