package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.VoluntarioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.VoluntarioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SedeRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.VoluntarioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.VoluntarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VoluntarioServiceImp implements VoluntarioService {
    private VoluntarioRepository voluntarioRepository;
    private SedeRepository sedeRepository;
    private VoluntarioDtoConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<VoluntarioDTO> findAll() {
        List<Voluntario> voluntarios = voluntarioRepository.findAll();
        return voluntarios.stream()
                .map(voluntario -> converter.convertToDto(voluntario))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VoluntarioDTO> findById(Long id) {
        Optional<Voluntario> voluntario = voluntarioRepository.findById(id);
        if (voluntario.isEmpty()) {
            return Optional.empty();
        }
        return voluntario.map(converter::convertToDto);
    }

    @Transactional
    public VoluntarioDTO save(VoluntarioDTO voluntarioDto) {
        Voluntario voluntario = converter.convertToEntity(voluntarioDto);
        List<Long> distinctSedes = voluntarioDto.getSedes().stream().distinct().toList();
        List<Sede> sedes = distinctSedes.stream()
                .map(sedeId -> sedeRepository.findById(sedeId)
                        .orElseThrow(() -> new EntityNotFoundException("Sede con id " + sedeId + " no encontrada")))
                .collect(Collectors.toList());
        voluntario.setSedes(sedes);
        Voluntario savedVoluntario = voluntarioRepository.save(voluntario);
        return converter.convertToDto(savedVoluntario);
    }

    @Override
    @Transactional
    public Optional<String> update(Long id, VoluntarioDTO voluntarioDto) {
        Voluntario voluntarioExistente = voluntarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voluntario con id " + id + " no encontrado"));
        voluntarioExistente.setNombre(voluntarioDto.getNombre());
        voluntarioExistente.setTipoVoluntario(voluntarioDto.getTipoVoluntario());
        voluntarioExistente.setDisponibilidad(voluntarioDto.isDisponibilidad());
        voluntarioExistente.setNumeroTrabajos(voluntarioDto.getNumeroTrabajos());
        // Actualizar las sedes
        List<Long> distinctSedes = voluntarioDto.getSedes().stream().distinct().toList();
        List<Sede> sedes = distinctSedes.stream()
                .map(sedeId -> sedeRepository.findById(sedeId)
                        .orElseThrow(() -> new EntityNotFoundException("Sede con id " + sedeId + " no encontrada")))
                .collect(Collectors.toList());
        voluntarioExistente.setSedes(sedes);
        voluntarioRepository.save(voluntarioExistente);
        return Optional.of("Voluntario actualizado con éxito");
    }

    @Override
    public Optional<String> delete(Long id) {
        if (voluntarioRepository.existsById(id)) {
            voluntarioRepository.deleteById(id);
            return Optional.of("Voluntario eliminado con éxito");
        }
        return Optional.of("Voluntario con id " + id + " no encontrado");
    }

    @Override
    public VoluntarioDTO findVoluntarioById(Long id) {
        return voluntarioRepository.findById(id)
                .map(voluntario -> converter.convertToDto(voluntario))
                .orElseThrow(() -> new EntityNotFoundException("Voluntario con id " + id + " no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<VoluntarioDTO> findByProfesionAndSede(String profesion, Long sedeId) {
        List<Voluntario> voluntarios = voluntarioRepository.findByProfesionAndSedesId(profesion, sedeId);
        return voluntarios.stream()
                .map(voluntario -> converter.convertToDto(voluntario))
                .collect(Collectors.toList());
    }

}
