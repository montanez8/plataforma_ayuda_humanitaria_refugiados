package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;
import java.util.Optional;

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

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SocioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

@RequestMapping("/api/socio")

public class SocioController {

    private SocioService socioService;

    @GetMapping // http://localhost:8080/api/socio
    public List<SocioDTO> getSocios() {
        return socioService.findAll();
    }

    @GetMapping("/{id}") // http://localhost:8080/api/socio/1
    public ResponseEntity<?> SocioById(@PathVariable Long id) {
        Optional<?> socioDTO = socioService.findById(id);
        if (socioDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el socio con el id: " + id + " solicitado");
        }
        return socioDTO.map(o -> ResponseEntity.status(HttpStatus.OK).body((SocioDTO) o))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping // http://localhost:8080/api/socio
    public ResponseEntity<?> save(@Valid @RequestBody Socio socio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(socioService.save(socio));
    }

    @PutMapping("/{id}") // http://localhost:8080/api/socio/1
    public ResponseEntity<?> update(@Valid @RequestBody Socio socio, @PathVariable Long id) {
        return socioService.update(id, socio)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>("Socio no encontrado", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}") // http://localhost:8080/api/socio/1
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<String> socio = socioService.delete(id);
        return socio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @GetMapping("/cuota/{tipoCuota}") //
    // http://localhost:8080/api/socios/cuota/tipoCuota
    // public ResponseEntity<List<SocioDTO>> getSociosByTipoCuota(@PathVariable
    // String tipoCuota) {
    // try {
    // Cuota cuota = Cuota.valueOf(tipoCuota.toUpperCase());
    // List<SocioDTO> socios = socioService.findByTipoCuota(cuota);
    // return ResponseEntity.ok(socios);
    // } catch (IllegalArgumentException e) {
    // return ResponseEntity.badRequest().build();
    // }
    // }
    @GetMapping("/cuota/{tipoCuota}") // http://localhost:8080/api/socios/cuota/tipoCuota
    public ResponseEntity<?> getSociosByTipoCuota(@PathVariable String tipoCuota) {
        Optional<List<SocioDTO>> socios = socioService.findByTipoCuota(tipoCuota);
        if (socios.isPresent()) {
            return ResponseEntity.ok(socios.get());
        } else {
            return ResponseEntity.badRequest()
                    .body("Tipo de cuota no válida. Solo se puede ingresar: MINIMA, MEDIA, MAXIMA");
        }
    }

}
