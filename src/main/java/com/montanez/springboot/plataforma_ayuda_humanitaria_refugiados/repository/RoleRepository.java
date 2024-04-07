package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Rol;

public interface RoleRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);

}
