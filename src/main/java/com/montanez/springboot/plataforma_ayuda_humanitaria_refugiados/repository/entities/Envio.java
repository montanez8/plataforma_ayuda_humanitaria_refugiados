package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String destino;
    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @ManyToMany
    @JoinTable(name = "envio_sede", joinColumns = @JoinColumn(name = "envio_id"), inverseJoinColumns = @JoinColumn(name = "sede_id"))
    private List<Sede> sedes;

    @ManyToMany
    @JoinTable(name = "envio_voluntario", joinColumns = @JoinColumn(name = "envio_id"), inverseJoinColumns = @JoinColumn(name = "voluntario_id"))
    private List<Voluntario> voluntarios;

}
