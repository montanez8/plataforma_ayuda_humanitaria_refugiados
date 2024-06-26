package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "voluntario")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "tipo_voluntario")
    @Enumerated(EnumType.STRING)
    private TipoVoluntario tipoVoluntario;
    private String profesion;
    private boolean disponibilidad;
    private int numeroTrabajos;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "voluntario_sede", joinColumns = @JoinColumn(name = "voluntario_id"), inverseJoinColumns = @JoinColumn(name = "sede_id"))
    private List<Sede> sedes;
}
