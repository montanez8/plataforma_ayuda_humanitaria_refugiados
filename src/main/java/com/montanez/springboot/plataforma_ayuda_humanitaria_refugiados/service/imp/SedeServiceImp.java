package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SedeDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.SedeDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SedeRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SedeService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SedeServiceImp implements SedeService {
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private SedeDtoConverter converter;

    @Transactional(readOnly = true)
    @Override
    public List<SedeDTO> findAll() {
        List<Sede> sedes = sedeRepository.findAll();
        return sedes.stream()
                .map(sede -> converter.convertToDto(sede))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SedeDTO> findById(Long id) {
        Optional<Sede> sede = sedeRepository.findById(id);
        if (sede.isEmpty()) {
            return Optional.empty();
        }
        return sede.map(converter::convertToDto);
    }

    @Override
    @Transactional
    public SedeDTO save(SedeDTO sede) {
        return converter.convertToDto(sedeRepository.save(converter.convertToEntity(sede)));
    }

    @Override
    public Optional<String> update(Long id, SedeDTO sede) {
        return sedeRepository.findById(id)
                .map(sedeUpdate -> {
                    sedeUpdate.setNombre(sede.getNombre());
                    sedeUpdate.setDirector(sede.getDirector());
                    sedeUpdate.setDomicilio(sede.getDomicilio());
                    converter.convertToDto(sedeRepository.save(sedeUpdate));
                    return "Sede actualizada correctamente";
                });
    }

    @Override
    @Transactional
    public Optional<String> delete(Long id) {
        Optional<Sede> sede = sedeRepository.findById(id);
        if (sede.isPresent()) {
            sedeRepository.deleteById(id);
            return Optional.of("Sede eliminada correctamente");
        } else {
            return Optional.of("Sede no encontrada");
        }
    }

    @Override
    public Sede findSedeById(Long id) {
        return sedeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la Sede con ID: " + id));
    }

}
