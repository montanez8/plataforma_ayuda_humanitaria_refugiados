package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnvioDTO {
    private Long id;
    @NotEmpty(message = "El código no puede estar vacío")
    private String codigo;
    @NotEmpty(message = "El origen no puede estar vacío")
    private String destino;
    @NotNull(message = "La fecha de envío no puede ser nula")
    @FutureOrPresent(message = "La fecha de envío debe ser en el presente o en el futuro")
    private LocalDate fechaEnvio;
    private List<Long> sedesIds;
    private List<Long> voluntariosIds;
}