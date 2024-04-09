package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.MaterialDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes.InformeSedeDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes.InformeSocioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.dto_converter_informes.InformeVoluntarioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeEnvioMaterialDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSedeDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSocioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeVoluntarioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.EnvioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SedeRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SocioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.VoluntarioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.InformeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InformeServiceImp implements InformeService {

    private SocioRepository socioRepository;
    private SedeRepository sedeRepository;
    private VoluntarioRepository voluntarioRepository;
    private EnvioRepository envioRepository;
    private InformeSocioDtoConverter converter;
    private InformeSedeDtoConverter informeSedeDtoConverter;
    private InformeVoluntarioDtoConverter informeVoluntarioDtoConverter;

    @Override
    public List<InformeSocioDto> informeSocios() {
        List<Socio> socios = socioRepository.findAll();
        return socios.stream()
                .map(converter::convertToDto).toList();
    }

    @Override
    public List<InformeSedeDto> informeSedes() {
        List<Sede> sedes = sedeRepository.findAll();
        return sedes.stream()
                .map(informeSedeDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InformeVoluntarioDto> informeVoluntarios() {
        List<Voluntario> voluntarios = voluntarioRepository.findAll();
        return voluntarios.stream()
                .map(informeVoluntarioDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InformeEnvioMaterialDto> informeMaterial() {
        List<Envio> envios = envioRepository.findAll();
        return envios.stream()
                .map(envio -> {
                    InformeEnvioMaterialDto dto = new InformeEnvioMaterialDto();
                    dto.setDestino(envio.getDestino());
                    dto.setFechaEnvio(envio.getFechaEnvio());

                    List<MaterialDTO> materiales = envio.getMateriales().stream()
                            .map(material -> {
                                MaterialDTO materialDto = new MaterialDTO();
                                materialDto.setNombre(material.getNombre());
                                materialDto.setCantidad(material.getCantidad());
                                return materialDto;
                            })
                            .collect(Collectors.toList());

                    dto.setMateriales(materiales);
                    return dto;
                })
                .collect(Collectors.toList());

    }

}
