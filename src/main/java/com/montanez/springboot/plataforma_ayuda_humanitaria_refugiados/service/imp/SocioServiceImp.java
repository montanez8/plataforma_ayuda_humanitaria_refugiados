package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.SocioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SocioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SocioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class SocioServiceImp implements SocioService {

    private SocioRepository socioRepository;

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

    @Override
    @Transactional
    public SocioDTO save(Socio socio) {
        return socioDtoConverter.convertToDto(socioRepository.save(socio));

    }


    @Override
    @Transactional
    public Optional<String>  update(Long id, Socio socio) {
        return socioRepository.findById(id)
                .map(socioUpdate -> {
            socioUpdate.setNombre(socio.getNombre());
            socioUpdate.setCuentaBancaria(socio.getCuentaBancaria());
            socioUpdate.setFechaPago(socio.getFechaPago());
            socioUpdate.setTipoCuota(socio.getTipoCuota());
            socioUpdate.setSede(socio.getSede());
            socioRepository.save(socioUpdate);
            return "Socio actualizado";
        });
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
}
