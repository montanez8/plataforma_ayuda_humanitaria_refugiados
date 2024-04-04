package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;
import java.util.Optional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SedeDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;

public interface SedeService {
    List<SedeDTO> findAll();

    Optional<SedeDTO> findById(Long id);

    SedeDTO save(SedeDTO sede);

    Optional<String> update(Long id, SedeDTO sede);

    Optional<String> delete(Long id);

    Sede findSedeById(Long id);
}
