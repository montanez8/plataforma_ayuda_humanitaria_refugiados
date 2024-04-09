package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;
import java.util.Optional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.VoluntarioDTO;

public interface VoluntarioService {
    List<VoluntarioDTO> findAll();

    Optional<VoluntarioDTO> findById(Long id);

    VoluntarioDTO save(VoluntarioDTO voluntario);

    Optional<String> update(Long id, VoluntarioDTO voluntario);

    Optional<String> delete(Long id);

    VoluntarioDTO findVoluntarioById(Long id);

    List<VoluntarioDTO> findByProfesionAndSede(String profesion, Long sedeId);

}
