package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.RefugioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.RefugioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_DIRECTOR')")
@RequestMapping("/refugios")
public class RefugioController {

    @Autowired
    private RefugioService refugioService;

    @GetMapping
    public List<RefugioDto> getAllRefugios() {
        return refugioService.findAll();
    }

    @GetMapping("/{id}") // http//localhost:8080/refugios/1
    public RefugioDto getRefugio(@PathVariable Long id) {
        return refugioService.findById(id);
    }

    @PostMapping // http//localhost:8080/refugios
    public ResponseEntity<?> save(@RequestBody RefugioDto refugioDto) {
        return ResponseEntity.ok(refugioService.save(refugioDto));
    }

    @PutMapping("/{id}")
    public RefugioDto update(@PathVariable Long id, @RequestBody RefugioDto refugioDto) {
        return refugioService.update(id, refugioDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRefugio(@PathVariable Long id) {
        refugioService.delete(id);
    }
}
