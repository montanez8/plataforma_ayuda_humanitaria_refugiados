package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSedeDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IsedeDtoConverter {
    private ModelMapper modelMapper;

    public InformeSedeDto convertToDto(Sede sede) {
        return modelMapper.map(sede, InformeSedeDto.class);
    }

    public Sede convertToEntity(InformeSedeDto informeSedeDto) {
        return modelMapper.map(informeSedeDto, Sede.class);
    }
}
