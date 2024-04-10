package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.RefugioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.RefugioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.RefugioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Refugio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.RefugioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RefugioServiceImp implements RefugioService {

    private RefugioRepository refugioRepository;

    private RefugioDtoConverter converter;

    @Override
    @Transactional
    public RefugioDto save(RefugioDto refugioDto) {
        return converter.convertToDto(refugioRepository.save(converter.convertToEntity(refugioDto)));
    }

    @Override
    @Transactional
    public RefugioDto update(long id, RefugioDto refugioDto) {
        Refugio existingRefugio = refugioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Refugio con id " + id + " no encontrado"));

        existingRefugio.setNombre(refugioDto.getNombre());
        existingRefugio.setUbicacion(refugioDto.getUbicacion());

        Refugio updatedRefugio = refugioRepository.save(existingRefugio);
        return converter.convertToDto(updatedRefugio);
    }

    @Override
    public List<RefugioDto> findAll() {
        return StreamSupport.stream(refugioRepository.findAll().spliterator(), false)
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(long id) {
        refugioRepository.deleteById(id);
    }

    @Override
    public RefugioDto findById(long id) {
        return refugioRepository.findById(id)
                .map(converter::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException("Refugio con id " + id + " no encontrado"));
    }

}
