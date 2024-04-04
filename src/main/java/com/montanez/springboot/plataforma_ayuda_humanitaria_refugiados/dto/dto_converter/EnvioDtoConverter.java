package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;

@Component
public class EnvioDtoConverter {
        private ModelMapper modelMapper;

        public EnvioDtoConverter() {
                modelMapper = new ModelMapper();

                Converter<List<Sede>, List<Long>> toDtoSedeConverter = context -> context.getSource() == null ? null
                                : context.getSource().stream().map(Sede::getId).collect(Collectors.toList());

                Converter<List<Long>, List<Sede>> toEntitySedeConverter = context -> context.getSource() == null ? null
                                : context.getSource().stream().map(id -> {
                                        Sede sede = new Sede();
                                        sede.setId(id);
                                        return sede;
                                }).collect(Collectors.toList());

                Converter<List<Voluntario>, List<Long>> toDtoVoluntarioConverter = context -> context
                                .getSource() == null ? null
                                                : context.getSource().stream().map(Voluntario::getId)
                                                                .collect(Collectors.toList());

                Converter<List<Long>, List<Voluntario>> toEntityVoluntarioConverter = context -> context
                                .getSource() == null ? null
                                                : context.getSource().stream().map(id -> {
                                                        Voluntario voluntario = new Voluntario();
                                                        voluntario.setId(id);
                                                        return voluntario;
                                                }).collect(Collectors.toList());

                modelMapper.typeMap(Envio.class, EnvioDTO.class)
                                .addMappings(mapper -> mapper.using(toDtoSedeConverter).map(Envio::getSedes,
                                                EnvioDTO::setSedesIds))
                                .addMappings(mapper -> mapper.using(toDtoVoluntarioConverter).map(Envio::getVoluntarios,
                                                EnvioDTO::setVoluntariosIds));

                modelMapper.typeMap(EnvioDTO.class, Envio.class)
                                .addMappings(mapper -> mapper.using(toEntitySedeConverter).map(EnvioDTO::getSedesIds,
                                                Envio::setSedes))
                                .addMappings(mapper -> mapper.using(toEntityVoluntarioConverter)
                                                .map(EnvioDTO::getVoluntariosIds, Envio::setVoluntarios));
        }

        public EnvioDTO convertToDto(Envio envio) {
                return modelMapper.map(envio, EnvioDTO.class);
        }

        public Envio convertToEntity(EnvioDTO envioDTO) {
                return modelMapper.map(envioDTO, Envio.class);
        }

}