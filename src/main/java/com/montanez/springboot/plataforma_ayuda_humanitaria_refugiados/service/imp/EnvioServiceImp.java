package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.MaterialDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.RefugioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.EnvioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_converter.RefugioDtoConverter;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.rabbit_mq.ShipmentCreatedEvent;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.EnvioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.MaterialRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.RefugioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.SedeRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.VoluntarioRepository;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Material;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Refugio;
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
        private RefugioRepository refugioRepository;
        private MaterialRepository materialRepository;
        private EnvioDtoConverter converter;
        private RefugioDtoConverter refugioDtoConverter;
        private RabbitTemplate rabbitTemplate;
        private MongoTemplate mongoTemplate;

        @Transactional(readOnly = true)
        @Override
        public List<EnvioDTO> findAll() {
                List<Envio> envios = envioRepository.findAll();
                return envios.stream().map(envio -> {
                        EnvioDTO dto = new EnvioDTO();
                        dto.setId(envio.getId());
                        dto.setCodigo(envio.getCodigo());
                        dto.setDestino(envio.getDestino());
                        dto.setFechaEnvio(envio.getFechaEnvio());
                        dto.setRefugio(refugioDtoConverter.convertToDto(envio.getRefugio()));
                        dto.setSedesIds(envio.getSedes().stream().map(Sede::getId).collect(Collectors.toList()));
                        dto.setVoluntariosIds(envio.getVoluntarios().stream().map(Voluntario::getId)
                                        .collect(Collectors.toList()));
                        dto.setMateriales(envio.getMateriales().stream().map(material -> {
                                MaterialDTO materialDto = new MaterialDTO();
                                materialDto.setNombre(material.getNombre());
                                materialDto.setCantidad(material.getCantidad());
                                return materialDto;
                        }).collect(Collectors.toList()));
                        return dto;
                }).collect(Collectors.toList());
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
                                        dto.setRefugio(refugioDtoConverter.convertToDto(envio.getRefugio()));
                                        dto.setSedesIds(envio.getSedes().stream().map(Sede::getId)
                                                        .collect(Collectors.toList()));
                                        dto.setVoluntariosIds(
                                                        envio.getVoluntarios().stream().map(Voluntario::getId)
                                                                        .collect(Collectors.toList()));
                                        dto.setMateriales(envio.getMateriales().stream().map(material -> {
                                                MaterialDTO materialDto = new MaterialDTO();
                                                materialDto.setNombre(material.getNombre());
                                                materialDto.setCantidad(material.getCantidad());
                                                return materialDto;
                                        }).collect(Collectors.toList()));
                                        return dto;
                                });
        }

        @Override
        @Transactional
        public EnvioDTO save(EnvioDTO envioDto) {
                Envio envio = converter.convertToEntity(envioDto);
                RefugioDto refugioDto = envioDto.getRefugio();
                if (refugioDto == null) {
                        throw new IllegalArgumentException("El refugio no puede ser null");
                }
                Long refugioId = refugioDto.getId();
                Refugio refugio = refugioRepository.findById(refugioId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Refugio con id " + refugioId + " no encontrado"));

                List<Long> distinctSedes = envioDto.getSedesIds().stream().distinct().toList();
                List<Sede> sedes = distinctSedes.stream()
                                .map(sedeId -> sedeRepository.findById(sedeId)
                                                .orElseThrow(() -> new EntityNotFoundException(
                                                                "Sede con id " + sedeId + " no encontrada")))
                                .collect(Collectors.toList());
                List<Long> distinctVoluntarios = envioDto.getVoluntariosIds().stream().distinct().toList();
                List<Voluntario> voluntarios = distinctVoluntarios.stream()
                                .map(voluntarioId -> voluntarioRepository.findById(voluntarioId)
                                                .orElseThrow(() -> new EntityNotFoundException(
                                                                "Voluntario con id " + voluntarioId
                                                                                + " no encontrado")))
                                .collect(Collectors.toList());
                List<Material> materiales = envioDto.getMateriales().stream()
                                .map(materialDto -> {
                                        Material material = new Material();
                                        material.setNombre(materialDto.getNombre());
                                        material.setCantidad(materialDto.getCantidad());
                                        material.setEnvio(envio);
                                        return materialRepository.save(material);
                                })
                                .collect(Collectors.toList());
                envio.setRefugio(refugio);
                envio.setVoluntarios(voluntarios);
                envio.setSedes(sedes);
                envio.setMateriales(materiales);
                Envio saveEnvio = envioRepository.save(envio);
                EnvioDTO saEnvioDTO = converter.convertToDto(saveEnvio);
                ShipmentCreatedEvent event = new ShipmentCreatedEvent(saEnvioDTO);
                rabbitTemplate.convertAndSend("colaEnvio", event);
                mongoTemplate.save(event);
                return saEnvioDTO;
        }

        @Override
        @Transactional
        public Optional<EnvioDTO> update(Long id, EnvioDTO envioDto) {
                return envioRepository.findById(id).map(envio -> {
                        envio.setCodigo(envioDto.getCodigo());
                        envio.setDestino(envioDto.getDestino());
                        envio.setFechaEnvio(envioDto.getFechaEnvio());

                        RefugioDto refugioDto = envioDto.getRefugio();
                        if (refugioDto == null) {
                                throw new IllegalArgumentException("El refugio no puede ser null");
                        }
                        Long refugioId = refugioDto.getId();
                        Refugio refugio = refugioRepository.findById(refugioId)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                        "Refugio con id " + refugioId + " no encontrado"));

                        List<Long> distinctSedes = envioDto.getSedesIds().stream().distinct().toList();
                        List<Sede> sedes = distinctSedes.stream()
                                        .map(sedeId -> sedeRepository.findById(sedeId)
                                                        .orElseThrow(() -> new EntityNotFoundException(
                                                                        "Sede con id " + sedeId + " no encontrada")))
                                        .collect(Collectors.toList());

                        List<Long> distinctVoluntarios = envioDto.getVoluntariosIds().stream().distinct().toList();
                        List<Voluntario> voluntarios = distinctVoluntarios.stream()
                                        .map(voluntarioId -> voluntarioRepository.findById(voluntarioId)
                                                        .orElseThrow(() -> new EntityNotFoundException(
                                                                        "Voluntario con id " + voluntarioId
                                                                                        + " no encontrado")))
                                        .collect(Collectors.toList());

                        List<Material> materiales = envioDto.getMateriales().stream()
                                        .map(materialDto -> {
                                                Material material = new Material();
                                                material.setNombre(materialDto.getNombre());
                                                material.setCantidad(materialDto.getCantidad());
                                                material.setEnvio(envio);
                                                return materialRepository.save(material);
                                        })
                                        .collect(Collectors.toList());
                        envio.setRefugio(refugio);
                        envio.setVoluntarios(voluntarios);
                        envio.setSedes(sedes);
                        envio.setMateriales(materiales);

                        Envio updatedEnvio = envioRepository.save(envio);
                        return converter.convertToDto(updatedEnvio);
                });
        }

        @Override
        public Optional<String> delete(Long id) {
                return envioRepository.findById(id).map(envio -> {

                        List<Material> materiales = envio.getMateriales();
                        for (Material material : materiales) {
                                materialRepository.delete(material);
                        }

                        envioRepository.delete(envio);
                        return "Envio con id " + id + " eliminado correctamente";
                })
                                .or(() -> Optional.of("Envio con id " + id + " no encontrado"));
        }

}
