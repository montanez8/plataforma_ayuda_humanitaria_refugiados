package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.RoleRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.UsuarioRepositiry;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Rol;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Usuario;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepositiry usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario user) {

        List<Rol> roles = new ArrayList<>();

        if (user.isAdmin()) {
            Optional<Rol> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        if (user.isDirector()) {
            Optional<Rol> optionalRoleDirector = roleRepository.findByName("ROLE_DIRECTOR");
            optionalRoleDirector.ifPresent(roles::add);
        }
        if (user.isAuxiliar()) {
            Optional<Rol> optionalRoleAuxiliar = roleRepository.findByName("ROLE_AUXILIAR");
            optionalRoleAuxiliar.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
}
