package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.deserializer.CuotaDeserializer;
import lombok.Data;

import java.time.LocalDate;
@Data
public class SocioDTO {
    private Long id;
    private String nombre;
    private String cuentaBancaria;
    private LocalDate fechaPago;
    @JsonDeserialize(using = CuotaDeserializer.class)
    private Cuota tipoCuota;
    private SedeDTO sede;
}
