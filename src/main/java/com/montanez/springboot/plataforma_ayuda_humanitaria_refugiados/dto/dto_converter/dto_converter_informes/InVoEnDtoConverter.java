package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeVoluntEnvioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InVoEnDtoConverter {
    private ModelMapper modelMapper;

    public InformeVoluntEnvioDto convertToDto(Voluntario voluntario) {
        return modelMapper.map(voluntario, InformeVoluntEnvioDto.class);
    }

    public Voluntario convertToEntity(InformeVoluntEnvioDto informeVoluntEnvioDto) {
        return modelMapper.map(informeVoluntEnvioDto, Voluntario.class);
    }
}
