package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voluntario")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "tipo_voluntario")
    @Enumerated(EnumType.STRING)
    private TipoVoluntario tipoVoluntario;
    private boolean disponibilidad;
    private int numeroTrabajos;

    @ManyToMany
    private List<Sede> sedes;
}
