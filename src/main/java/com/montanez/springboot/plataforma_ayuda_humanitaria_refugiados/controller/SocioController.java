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

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SocioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SocioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_DIRECTOR')")
@RequestMapping("/api/socio")
public class SocioController {
    @Autowired
    private SocioService socioService;
    private final String EXP_SOCIO = "{\n" +
            "    \"nombre\": \"\",\n" +
            "    \"cuentaBancaria\": \"0123456789\",\n" +
            "    \"fechaPago\": \"2023-03-01\",\n" +
            "    \"tipoCuota\": \"MEDIA\",\n" +
            "    \"sede\": {\n" +
            "        \"id\": 10\n" +
            "    }\n" +
            "}";

    @GetMapping // http://localhost:8080/api/socio
    @Operation(summary = "Obtener todos los socios", description = "Obtiene todos los socios registrados en la base de datos")
    public List<SocioDTO> getSocios() {
        return socioService.findAll();
    }

    @GetMapping("/{id}") // http://localhost:8080/api/socio/1
    @Operation(summary = "Obtener un socio por id", description = "Obtiene un socio por el id especificado")
    public ResponseEntity<?> SocioById(@PathVariable Long id) {
        Optional<?> socioDTO = socioService.findById(id);
        if (socioDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el socio con el id: " + id + " solicitado");
        }
        return socioDTO.map(o -> ResponseEntity.status(HttpStatus.OK).body((SocioDTO) o))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo socio", description = "Guarda un nuevo socio en la base de datos")
    public ResponseEntity<?> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos para actualizar un socio", content = @Content(examples = @ExampleObject(name = "Ejemplo", value = EXP_SOCIO))) @RequestBody @Valid Socio socio) {
        return ResponseEntity.ok(socioService.save(socio));
    }

    @PutMapping("/{id}") // http://localhost:8080/api/socio/1
    @Operation(summary = "Actualizar un socio", description = "Actualiza el socio con el id especificado")
    public ResponseEntity<?> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos para actualizar un socio", content = @Content(examples = @ExampleObject(name = "Ejemplo", value = EXP_SOCIO))) @Valid @RequestBody Socio socio,
            @PathVariable Long id) {
        return socioService.update(id, socio)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>("Socio no encontrado", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}") // http://localhost:8080/api/socio/1
    @Operation(summary = "Eliminar un socio", description = "Elimina el socio con el id especificado")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<String> socio = socioService.delete(id);
        return socio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cuota/{tipoCuota}") // http://localhost:8080/api/socios/cuota/tipoCuota
    @Operation(summary = "Obtener socios por tipo de cuota", description = "Obtiene todos los socios registrados en la base de datos que tengan el tipo de cuota ingresado")
    public ResponseEntity<?> getSociosByTipoCuota(
            @Parameter(description = "Tipo de cuota a buscar", required = true) @PathVariable @Schema(allowableValues = {
                    "MINIMA", "MEDIA", "MAXIMA" }) String tipoCuota) {
        Optional<List<SocioDTO>> socios = socioService.findByTipoCuota(tipoCuota);
        if (socios.isPresent()) {
            return ResponseEntity.ok(socios.get());
        } else {
            return ResponseEntity.badRequest()
                    .body("Tipo de cuota no válida. Solo se puede ingresar: MINIMA, MEDIA, MAXIMA");
        }
    }

}
