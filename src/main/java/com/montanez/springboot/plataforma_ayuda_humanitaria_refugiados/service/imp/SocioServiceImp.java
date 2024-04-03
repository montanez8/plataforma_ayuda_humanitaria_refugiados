package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.SocioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SocioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SocioServiceImp implements SocioService {
    @Autowired
    private SocioRepository socioRepository;
    @Autowired
    private SocioDtoConverter socioDtoConverter;


    @Override
    public Socio save(Socio socio) {
        return socioRepository.save(socio);
    }

    @Override
    public List<SocioDTO> getSocios() {
        List<SocioDTO> socioDTOS = new ArrayList<>();
        socioRepository.findAll().forEach(socio -> {
            socioDTOS.add(socioDtoConverter.convertToDto(socio));
        });
        return socioDTOS;
    }


    @Override
    public SocioDTO getSocioById(Long id) {
        return socioDtoConverter.convertToDto(
                socioRepository.findById(id).orElse(null));
    }

    @Override
    public void delete(Long id) {
        socioRepository.deleteById(id);
    }

    @Override
    public Socio update(Long id, Socio socio) {
        Socio socioUpdate = socioRepository.findById(id).orElse(null);
        if (socioUpdate != null) {
            socioUpdate.setNombre(socio.getNombre());
            socioUpdate.setCuentaBancaria(socio.getCuentaBancaria());
            socioUpdate.setFechaPago(socio.getFechaPago());
            socioUpdate.setTipoCuota(socio.getTipoCuota());
            socioUpdate.setSede(socio.getSede());
            socioRepository.save(socioUpdate);
        }
        return socioUpdate;
    }
}
