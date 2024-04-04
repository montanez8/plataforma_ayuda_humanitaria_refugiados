package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;
import java.util.Optional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;

public interface SocioService {
    List<SocioDTO> findAll();

    Optional<SocioDTO> findById(Long id);

    SocioDTO save(Socio socio);

    Optional<String> update(Long id, Socio socio);

    Optional<String> delete(Long id);

    List<SocioDTO> findByTipoCuota(Cuota tipoCuota);

    Optional<List<SocioDTO>> findByTipoCuota(String tipoCuota);
}
