package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SedeDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SedeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_DIRECTOR')")
@RequestMapping("/api/sede")
public class SedeController {
    @Autowired
    private SedeService sedeService;
    private final String EXP_SEDE = "{\n" + "    \"nombre\": \" \",\n"
            + "    \"domicilio\": \" \",\n" + "    \"director\": \" \"\n"
            + "}";

    @Operation(summary = "Obtener todas las sedes")
    @GetMapping // http://localhost:8080/api/sede
    public List<SedeDTO> findAll() {
        return sedeService.findAll();
    }

    @Operation(summary = "Obtener una sede por id")
    @GetMapping("/{id}") // http://localhost:8080/api/sede/id
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<SedeDTO> sedeDTO = sedeService.findById(id);
        if (sedeDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la sede con el id: " + id + " solicitado");
        }
        return sedeDTO.map(o -> ResponseEntity.status(HttpStatus.OK).body(o))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crear una nueva sede")
    @PostMapping
    public ResponseEntity<?> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos para crear una nueva sede", content = @Content(examples = @ExampleObject(name = "Ejemplo", value = EXP_SEDE))) @Valid @RequestBody SedeDTO sede) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sedeService.save(sede));
    }

    @Operation(summary = "Actualizar una sede")
    @PutMapping("/{id}") // http://localhost:8080/api/sede/id
    public ResponseEntity<?> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos para crear una nueva sede", content = @Content(examples = @ExampleObject(name = "Ejemplo", value = EXP_SEDE))) @Valid @RequestBody SedeDTO sede,
            @PathVariable Long id) {
        return sedeService.update(id, sede).map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>("Sede no encontrada", HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar una sede")
    @DeleteMapping("/{id}") // http://localhost:8080/api/sede/id
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<String> sede = sedeService.delete(id);
        return sede.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
