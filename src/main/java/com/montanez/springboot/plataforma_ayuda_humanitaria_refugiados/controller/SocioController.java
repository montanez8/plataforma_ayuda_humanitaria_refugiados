package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socio")

public class SocioController {
    @Autowired
    private SocioService socioService;
    @GetMapping//http://localhost:8080/api/socio
    public List<SocioDTO> getSocios() {
        return socioService.getSocios();
    }

    @GetMapping("/{id}")//http://localhost:8080/api/socio/1
    public SocioDTO getSocioById(@PathVariable Long id) {
        return socioService.getSocioById(id);
    }

    @PostMapping//http://localhost:8080/api/socio
    public ResponseEntity<?> save(@RequestBody Socio socio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(socioService.save(socio));
    }
}
