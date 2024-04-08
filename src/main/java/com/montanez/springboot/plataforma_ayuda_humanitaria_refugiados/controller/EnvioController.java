package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Sede;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Voluntario;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.EnvioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/envio")
public class EnvioController {
    private EnvioService envioService;

    @GetMapping // http://localhost:8080/api/envio
    public ResponseEntity<List<EnvioDTO>> getAllEnvios() {
        List<Envio> envios = envioService.findAll();

        List<EnvioDTO> enviosDto = envios.stream().map(envio -> {
            EnvioDTO dto = new EnvioDTO();
            dto.setId(envio.getId());
            dto.setCodigo(envio.getCodigo());
            dto.setDestino(envio.getDestino());
            dto.setFechaEnvio(envio.getFechaEnvio());
            dto.setSedesIds(envio.getSedes().stream().map(Sede::getId).collect(Collectors.toList()));
            dto.setVoluntariosIds(envio.getVoluntarios().stream().map(Voluntario::getId).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(enviosDto);
    }

    @GetMapping("/{id}") // http://localhost:8080/api/envio/id
    public ResponseEntity<EnvioDTO> getEnvioById(@PathVariable Long id) {
        return envioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // http://localhost:8080/api/envio
    public ResponseEntity<EnvioDTO> saveEnvio(@Valid @RequestBody EnvioDTO envioDto) {
        EnvioDTO savedEnvioDto = envioService.save(envioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEnvioDto);
    }

    @PutMapping("/{id}") // http://localhost:8080/api/envio/id
    public ResponseEntity<EnvioDTO> updateEnvio(@Valid @RequestBody EnvioDTO envioDto, @PathVariable Long id) {
        return envioService.update(id, envioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // http://localhost:8080/api/envio/id
    public ResponseEntity<String> deleteEnvio(@PathVariable Long id) {
        return envioService.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
