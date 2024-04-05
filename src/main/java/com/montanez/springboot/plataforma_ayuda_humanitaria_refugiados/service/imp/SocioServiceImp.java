package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.SocioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.exception.SedeNotFoundException;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SedeRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SocioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SocioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SocioServiceImp implements SocioService {

    private SocioRepository socioRepository;
    private SedeRepository sedeRepository;

    private SocioDtoConverter socioDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public List<SocioDTO> findAll() {
        List<SocioDTO> socioDTOS = new ArrayList<>();
        socioRepository.findAll().forEach(socio -> socioDTOS.add(socioDtoConverter.convertToDto(socio)));
        return socioDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SocioDTO> findById(Long id) {
        Optional<Socio> socio = socioRepository.findById(id);
        if (socio.isEmpty()) {
            return Optional.empty();

        }
        return socio.map(socioDtoConverter::convertToDto);
    }

    @Transactional
    public SocioDTO save(Socio socio) {
        Optional<Sede> optionalSede = sedeRepository.findById(socio.getSede().getId());
        if (optionalSede.isPresent()) {
            socio.setSede(optionalSede.get());
            Socio savedSocio = socioRepository.save(socio);
            return socioDtoConverter.convertToDto(savedSocio);
        } else {
            List<Sede> sedes = sedeRepository.findAll();
            List<String> sedesExistente = sedes.stream()
                    .map(sede -> String.format("id: %d, nombre: %s", sede.getId(), sede.getNombre()))
                    .collect(Collectors.toList());
            throw new SedeNotFoundException(String.format("Sede con id %d no encontrada.", socio.getSede().getId()),
                    sedesExistente);
        }
    }

    @Override
    @Transactional
    public Optional<String> update(Long id, Socio socio) {
        Optional<Sede> optionalSede = sedeRepository.findById(socio.getSede().getId());
        if (optionalSede.isPresent()) {
            return socioRepository.findById(id)
                    .map(socioUpdate -> {
                        socioUpdate.setNombre(socio.getNombre());
                        socioUpdate.setCuentaBancaria(socio.getCuentaBancaria());
                        socioUpdate.setFechaPago(socio.getFechaPago());
                        socioUpdate.setTipoCuota(socio.getTipoCuota());
                        socioUpdate.setSede(optionalSede.get());
                        socioRepository.save(socioUpdate);
                        return Optional.of("Socio actualizado");
                    }).orElseThrow(() -> new RuntimeException("Socio con id " + id + " no encontrado"));
        } else {
            List<Sede> sedes = sedeRepository.findAll();
            List<String> sedesExistente = sedes.stream()
                    .map(sede -> String.format("id: %d, nombre: %s", sede.getId(), sede.getNombre()))
                    .collect(Collectors.toList());
            throw new SedeNotFoundException(String.format("Sede con id %d no encontrada.", socio.getSede().getId()),
                    sedesExistente);
        }
    }

    @Override
    @Transactional
    public Optional<String> delete(Long id) {
        Optional<Socio> socio = socioRepository.findById(id);
        if (socio.isPresent()) {
            socioRepository.deleteById(id);
            return Optional.of("Socio eliminado");
        } else {
            return Optional.of("No se encontró el socio con el id: " + id + " solicitado");
        }
    }

    @Override
    public List<SocioDTO> findByTipoCuota(Cuota cuota) {
        List<Socio> socios = socioRepository.findByTipoCuota(cuota);
        return socios.stream()
                .map(socio -> socioDtoConverter.convertToDto(socio))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<SocioDTO>> findByTipoCuota(String tipoCuota) {
        try {
            Cuota cuota = Cuota.valueOf(tipoCuota.toUpperCase());
            List<Socio> socios = socioRepository.findByTipoCuota(cuota);
            List<SocioDTO> socioDTOs = socios.stream()
                    .map(socio -> socioDtoConverter.convertToDto(socio))
                    .collect(Collectors.toList());
            return Optional.of(socioDTOs);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
