package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocioDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public SocioDTO convertToDto(Socio socio) {
        return modelMapper.map(socio, SocioDTO.class);
    }

    public Socio convertToEntity(SocioDTO socioDTO) {
        return modelMapper.map(socioDTO, Socio.class);
    }
}
