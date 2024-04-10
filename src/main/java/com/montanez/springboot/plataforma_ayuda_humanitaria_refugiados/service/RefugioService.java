package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.RefugioDto;

public interface RefugioService {

    List<RefugioDto> findAll();

    RefugioDto findById(long id);

    RefugioDto save(RefugioDto refugioDto);

    void delete(long id);

    RefugioDto update(long id, RefugioDto refugioDto);
}
