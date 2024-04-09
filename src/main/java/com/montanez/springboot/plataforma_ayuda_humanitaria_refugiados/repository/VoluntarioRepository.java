package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    List<Voluntario> findByProfesionAndSedesId(String profesion, Long sedeId);
}
