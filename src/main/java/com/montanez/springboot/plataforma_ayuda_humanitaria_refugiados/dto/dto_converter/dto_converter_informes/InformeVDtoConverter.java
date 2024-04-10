package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeVoluntarioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InformeVDtoConverter {

    private ModelMapper modelMapper;

    public InformeVoluntarioDto convertToDto(Voluntario voluntario) {
        return modelMapper.map(voluntario, InformeVoluntarioDto.class);
    }

    public Voluntario convertToEntity(InformeVoluntarioDto informeVoluntarioDto) {
        return modelMapper.map(informeVoluntarioDto, Voluntario.class);
    }
}
