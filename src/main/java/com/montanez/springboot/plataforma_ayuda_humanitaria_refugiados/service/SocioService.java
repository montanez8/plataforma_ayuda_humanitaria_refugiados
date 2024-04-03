package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;

import java.util.List;

public interface SocioService {
    Socio save(Socio socio);

     List<SocioDTO> getSocios();

     SocioDTO getSocioById(Long id);
     void delete(Long id);

     Socio update(Long id ,Socio socio);
}
