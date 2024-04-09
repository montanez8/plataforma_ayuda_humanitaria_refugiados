package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeEvoluntarioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IEnvVoDtoConverter {
    private ModelMapper modelMapper;

    public InformeEvoluntarioDto convertToDto(Envio envio) {
        return modelMapper.map(envio, InformeEvoluntarioDto.class);
    }

    public Envio convertToEntity(InformeEvoluntarioDto informeEnvioVoluntarioDto) {
        return modelMapper.map(informeEnvioVoluntarioDto, Envio.class);
    }
}
