package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();

    Usuario save(Usuario user);

    boolean existsByUsername(String username);

}
