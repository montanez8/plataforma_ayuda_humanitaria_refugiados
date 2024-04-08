package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes;

import java.time.LocalDate;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;

import lombok.Data;

@Data
public class InformeSocioDto {
    private String nombre;
    private String cuentaBancaria;
    private LocalDate fechaPago;
    private Cuota tipoCuota;
}
