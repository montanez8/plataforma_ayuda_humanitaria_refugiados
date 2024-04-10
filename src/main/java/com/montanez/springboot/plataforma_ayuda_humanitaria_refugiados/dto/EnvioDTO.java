package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Modelo de datos para los envíos")
@Data
public class EnvioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotEmpty(message = "El código no puede estar vacío")
    private String codigo;
    @NotEmpty(message = "El origen no puede estar vacío")
    private String destino;
    @NotNull(message = "La fecha de envío no puede ser nula")
    @FutureOrPresent(message = "La fecha de envío debe ser en el presente o en el futuro")
    private LocalDate fechaEnvio;
    private RefugioDto refugio;
    private List<Long> sedesIds;
    private List<Long> voluntariosIds;
    private List<MaterialDTO> materiales;
}