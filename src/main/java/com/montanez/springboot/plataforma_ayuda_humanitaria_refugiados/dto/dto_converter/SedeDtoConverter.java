package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SedeDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SedeDtoConverter {
    private ModelMapper modelMapper;

    public SedeDTO convertToDto(Sede sede) {
        return modelMapper.map(sede, SedeDTO.class);
    }

    public Sede convertToEntity(SedeDTO sedeDTO) {
        return modelMapper.map(sedeDTO, Sede.class);
    }
}
