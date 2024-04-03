package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;

import java.util.List;
import java.util.Optional;

public interface SocioService {
    List<SocioDTO> findAll();

    Optional<SocioDTO> findById(Long id);
    SocioDTO save(Socio socio);
    Optional<String>  update(Long id ,Socio socio);
     Optional<String> delete(Long id);
}
