package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSedeDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InformeSedeDtoConverter {
    private ModelMapper modelMapper;

    public InformeSedeDto convertToDto(Sede sede) {
        return modelMapper.map(sede, InformeSedeDto.class);
    }

    public Sede convertToEntity(InformeSedeDto informeSedeDto) {
        return modelMapper.map(informeSedeDto, Sede.class);
    }
}