package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.annotation.ValidCuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.deserializer.CuotaDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "socio")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @Column(name = "cuenta_bancaria")
    @NotEmpty(message = "La cuenta bancaria no puede estar vacía")
    @Pattern(regexp = "\\d{10}", message = "La cuenta bancaria debe tener exactamente 10 dígitos")
    private String cuentaBancaria;
    @Column(name = "fecha_pago")
    @NotNull(message = "La fecha de pago no Null")
    private LocalDate fechaPago;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuota")
    @JsonDeserialize(using = CuotaDeserializer.class)
    @ValidCuota(message = "tipoCuota no es válida")
    @NotNull(message = "tipoCuota no es valida")
    private Cuota tipoCuota;
    @ManyToOne
    @NotNull(message = "sede no puede estar Null")
    private Sede sede;
}
