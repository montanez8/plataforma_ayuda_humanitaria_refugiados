package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.VoluntarioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VoluntarioDtoConverter {
    private ModelMapper modelMapper;

    public VoluntarioDtoConverter() {
        modelMapper = new ModelMapper();

        Converter<List<Sede>, List<Long>> toDtoConverter = new Converter<>() {
            public List<Long> convert(MappingContext<List<Sede>, List<Long>> context) {
                return context.getSource() == null ? null
                        : context.getSource().stream().map(Sede::getId).collect(Collectors.toList());
            }
        };

        Converter<List<Long>, List<Sede>> toEntityConverter = new Converter<>() {
            public List<Sede> convert(MappingContext<List<Long>, List<Sede>> context) {
                return context.getSource() == null ? null : context.getSource().stream().map(id -> {
                    Sede sede = new Sede();
                    sede.setId(id);
                    return sede;
                }).collect(Collectors.toList());
            }
        };

        modelMapper.typeMap(Voluntario.class, VoluntarioDTO.class)
                .addMappings(mapper -> mapper.using(toDtoConverter).map(Voluntario::getSedes, VoluntarioDTO::setSedes));
        modelMapper.typeMap(VoluntarioDTO.class, Voluntario.class).addMappings(
                mapper -> mapper.using(toEntityConverter).map(VoluntarioDTO::getSedes, Voluntario::setSedes));
    }

    public VoluntarioDTO convertToDto(Voluntario voluntario) {
        return modelMapper.map(voluntario, VoluntarioDTO.class);
    }

    public Voluntario convertToEntity(VoluntarioDTO voluntarioDTO) {
        return modelMapper.map(voluntarioDTO, Voluntario.class);
    }
}
