package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeEnvioMaterialDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IEMDtoConverter {
    private ModelMapper modelMapper;

    public InformeEnvioMaterialDto convertToDto(Envio envio) {
        return modelMapper.map(envio, InformeEnvioMaterialDto.class);
    }

    public Envio convertToEntity(InformeEnvioMaterialDto informeEnvioMaterialDto) {
        return modelMapper.map(informeEnvioMaterialDto, Envio.class);
    }
}
