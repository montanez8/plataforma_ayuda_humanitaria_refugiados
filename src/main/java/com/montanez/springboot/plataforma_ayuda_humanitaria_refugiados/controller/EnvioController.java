package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;

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

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_DIRECTOR')")
@RequestMapping("api/envio")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    private final String EXP_ENVIO = "{\n" +
            "    \"codigo\": \"ENV123\",\n" +
            "    \"destino\": \"Destino Ejemplo\",\n" +
            "    \"fechaEnvio\": \"2025-01-01\",\n" +
            "    \"refugio\": {\"id\": 1},\n" +
            "    \"sedesIds\": [1],\n" +
            "    \"voluntariosIds\": [1],\n" +
            "    \"materiales\": [\n" +
            "        {\n" +
            "            \"nombre\": \"Material Ejemplo 1\",\n" +
            "            \"cantidad\": 10\n" +
            "        },\n" +
            "        {\n" +
            "            \"nombre\": \"Material Ejemplo 2\",\n" +
            "            \"cantidad\": 20\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @GetMapping // http://localhost:8080/api/envio
    @ApiResponse(responseCode = "200", description = "Listado de envios")
    @Operation(summary = "Listar envios", description = "Listar todos los envios")
    public ResponseEntity<List<EnvioDTO>> getAllEnvios() {
        List<EnvioDTO> enviosDto = envioService.findAll();
        return ResponseEntity.ok(enviosDto);
    }

    @GetMapping("/{id}") // http://localhost:8080/api/envio/id
    @ApiResponse(responseCode = "200", description = "Envio encontrado")
    @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    @Operation(summary = "Obtener envio por id", description = "Obtener un envio por el id especificado")
    public ResponseEntity<EnvioDTO> getEnvioById(@PathVariable Long id) {
        return envioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // http://localhost:8080/api/envio
    @ApiResponse(responseCode = "201", description = "Envio creado")
    @Operation(summary = "Crear envio", description = "Crear un nuevo envio")
    public ResponseEntity<EnvioDTO> saveEnvio(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Ejemplo", value = EXP_ENVIO))) @Valid @RequestBody EnvioDTO envioDto) {
        EnvioDTO savedEnvioDto = envioService.save(envioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEnvioDto);
    }

    @PutMapping("/{id}") // http://localhost:8080/api/envio/id
    @ApiResponse(responseCode = "200", description = "Envio actualizado")
    @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    @Operation(summary = "Actualizar envio", description = "Actualizar un envio por el id especificado")
    public ResponseEntity<EnvioDTO> updateEnvio(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Ejemplo", value = EXP_ENVIO))) @Valid @RequestBody EnvioDTO envioDto,
            @PathVariable Long id) {
        return envioService.update(id, envioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // http://localhost:8080/api/envio/id
    @ApiResponse(responseCode = "200", description = "Envio eliminado")
    @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    @Operation(summary = "Eliminar envio", description = "Eliminar un envio por el id especificado")
    public ResponseEntity<String> deleteEnvio(@PathVariable Long id) {
        return envioService.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
