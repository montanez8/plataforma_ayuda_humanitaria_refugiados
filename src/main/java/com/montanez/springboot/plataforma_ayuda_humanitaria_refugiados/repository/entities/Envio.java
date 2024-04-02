package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
