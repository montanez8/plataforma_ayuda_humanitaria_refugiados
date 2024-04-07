package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Usuario;

public interface UsuarioRepositiry extends JpaRepository<Usuario, Long> {

    boolean existsByUsername(String username);

    Optional<Usuario> findByUsername(String username);
}
