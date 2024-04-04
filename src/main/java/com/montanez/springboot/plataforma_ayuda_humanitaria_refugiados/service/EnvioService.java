package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;
import java.util.Optional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;

public interface EnvioService {
    List<Envio> findAll();

    Optional<EnvioDTO> findById(Long id);

    EnvioDTO save(EnvioDTO envio);

    Optional<String> update(Long id, EnvioDTO envio);

    Optional<String> delete(Long id);

}
