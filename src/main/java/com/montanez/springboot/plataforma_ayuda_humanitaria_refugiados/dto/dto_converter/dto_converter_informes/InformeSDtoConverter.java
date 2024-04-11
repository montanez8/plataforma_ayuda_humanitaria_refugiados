package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSocioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InformeSDtoConverter {
    private ModelMapper modelMapper;

    public InformeSocioDto convertToDto(Socio socio) {
        return modelMapper.map(socio, InformeSocioDto.class);
    }

    public Socio convertToEntity(InformeSocioDto informeSocioDto) {
        return modelMapper.map(informeSocioDto, Socio.class);
    }
}
