package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.SedeDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SedeNameDtoConverter {
    private ModelMapper modelMapper;

    public SedeDto convertToDto(Sede sede) {
        return modelMapper.map(sede, SedeDto.class);
    }

    public Sede convertToEntity(SedeDto SedeDto) {
        return modelMapper.map(SedeDto, Sede.class);
    }
}
