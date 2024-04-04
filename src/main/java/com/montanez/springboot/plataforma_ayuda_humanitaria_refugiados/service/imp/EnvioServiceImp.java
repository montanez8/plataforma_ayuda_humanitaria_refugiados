package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.EnvioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.EnvioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SedeRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.VoluntarioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.EnvioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnvioServiceImp implements EnvioService {
    private EnvioRepository envioRepository;
    private SedeRepository sedeRepository;
    private VoluntarioRepository voluntarioRepository;
    private EnvioDtoConverter converter;

    @Transactional(readOnly = true)
    @Override
    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EnvioDTO> findById(Long id) {
        return envioRepository.findById(id)
                .map(envio -> {
                    EnvioDTO dto = new EnvioDTO();
                    dto.setId(envio.getId());
                    dto.setCodigo(envio.getCodigo());
                    dto.setDestino(envio.getDestino());
                    dto.setFechaEnvio(envio.getFechaEnvio());
                    dto.setSedesIds(envio.getSedes().stream().map(Sede::getId).collect(Collectors.toList()));
                    dto.setVoluntariosIds(
                            envio.getVoluntarios().stream().map(Voluntario::getId).collect(Collectors.toList()));
                    return dto;
                });
    }

    @Override
    @Transactional
    public EnvioDTO save(EnvioDTO envioDto) {
        Envio envio = converter.convertToEntity(envioDto);
        List<Long> distinctSedes = envioDto.getSedesIds().stream().distinct().toList();
        List<Sede> sedes = distinctSedes.stream()
                .map(sedeId -> sedeRepository.findById(sedeId)
                        .orElseThrow(() -> new EntityNotFoundException("Sede con id " + sedeId + " no encontrada")))
                .collect(Collectors.toList());
        List<Long> distinctVoluntarios = envioDto.getVoluntariosIds().stream().distinct().toList();
        List<Voluntario> voluntarios = distinctVoluntarios.stream()
                .map(voluntarioId -> voluntarioRepository.findById(voluntarioId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Voluntario con id " + voluntarioId + " no encontrado")))
                .collect(Collectors.toList());
        envio.setVoluntarios(voluntarios);
        envio.setSedes(sedes);
        Envio saveEnvio = envioRepository.save(envio);
        return converter.convertToDto(saveEnvio);
    }

    @Override
    public Optional<String> update(Long id, EnvioDTO envio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<String> delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // @Override
    // public EnvioDTO save(EnvioDTO envio) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'save'");
    // }

}
