package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.VoluntarioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.VoluntarioService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_DIRECTOR')")
@RequestMapping("/api/voluntario")
@AllArgsConstructor
public class VoluntarioController {
    private VoluntarioService voluntarioService;

    @GetMapping // http://localhost:8080/api/voluntario
    @Operation(summary = "Listar todos los voluntarios")
    public List<VoluntarioDTO> findAll() {
        return voluntarioService.findAll();
    }

    @GetMapping("/{id}") // http://localhost:8080/api/voluntario/id
    @Operation(summary = "Buscar voluntario por id")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<VoluntarioDTO> voluntarioDTO = voluntarioService.findById(id);
        if (voluntarioDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No se encontró el voluntario con el id: " + id + " solicitado");
        }
        return voluntarioDTO
                .map(o -> ResponseEntity.status(HttpStatus.OK)
                        .body(o))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping // http://localhost:8080/api/voluntario
    @Operation(summary = "Crear un voluntario")
    public ResponseEntity<VoluntarioDTO> saveVoluntario(@RequestBody VoluntarioDTO voluntarioDto) {
        VoluntarioDTO savedVoluntarioDto = voluntarioService.save(voluntarioDto);
        return new ResponseEntity<>(savedVoluntarioDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // http://localhost:8080/api/voluntario/id
    @Operation(summary = "Actualizar un voluntario")
    public ResponseEntity<String> updateVoluntario(@PathVariable Long id, @RequestBody VoluntarioDTO voluntarioDto) {
        Optional<String> result = voluntarioService.update(id, voluntarioDto);
        return ResponseEntity.ok(result.get());
    }

    @DeleteMapping("/{id}") // http://localhost:8080/api/voluntario/id
    @Operation(summary = "Eliminar un voluntario")
    public ResponseEntity<String> deleteVoluntario(@PathVariable Long id) {
        Optional<String> result = voluntarioService.delete(id);
        return ResponseEntity.ok(result.get());
    }

    // En VoluntarioController.java
    @GetMapping("/profesion/{profesion}/sede/{sedeId}") // http://localhost:8080/api/voluntario/profesion/{profesion}/sede/{sedeId}
    @Operation(summary = "Buscar voluntarios por profesión y sede")
    public List<VoluntarioDTO> findByProfesionAndSede(@PathVariable String profesion, @PathVariable Long sedeId) {
        return voluntarioService.findByProfesionAndSede(profesion, sedeId);
    }

}
