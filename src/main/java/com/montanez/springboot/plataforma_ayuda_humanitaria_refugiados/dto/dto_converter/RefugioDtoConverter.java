package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.RefugioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Refugio;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RefugioDtoConverter {
    private ModelMapper modelMapper;

    public RefugioDto convertToDto(Refugio refugio) {
        return modelMapper.map(refugio, RefugioDto.class);
    }

    public Refugio convertToEntity(RefugioDto refugioDto) {
        return modelMapper.map(refugioDto, Refugio.class);
    }
}
