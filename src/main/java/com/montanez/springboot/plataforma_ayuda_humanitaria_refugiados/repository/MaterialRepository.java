package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
