package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.MaterialDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Material;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MaterialDtoConverter {
    private ModelMapper modelMapper;

    public MaterialDTO convertToDto(Material material) {
        return modelMapper.map(material, MaterialDTO.class);
    }

    public Material convertToEntity(MaterialDTO materialDto) {
        return modelMapper.map(materialDto, Material.class);
    }
}
