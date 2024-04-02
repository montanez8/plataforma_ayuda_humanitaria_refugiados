package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "socio")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "cuenta_bancaria")
    private String cuentaBancaria;
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuota")
    private Cuota tipoCuota;
    @ManyToOne
    private Sede sede;

}
