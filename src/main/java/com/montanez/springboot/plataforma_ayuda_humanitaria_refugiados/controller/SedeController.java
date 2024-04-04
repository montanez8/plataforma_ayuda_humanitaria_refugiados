package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.SedeDTO;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.SedeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sede")
public class SedeController {
    private SedeService sedeService;

    @GetMapping//http://localhost:8080/api/sede
    public List<SedeDTO> findAll() {
        return sedeService.findAll();
    }
    @GetMapping("/{id}")//http://localhost:8080/api/sede/id
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<SedeDTO> sedeDTO = sedeService.findById(id);
        if(sedeDTO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la sede con el id: " + id + " solicitado");
        }
        return sedeDTO
                .map(o -> ResponseEntity.status(HttpStatus.OK)
                        .body(o)).orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping//http://localhost:8080/api/sede
    public ResponseEntity<?> save(@Valid @RequestBody SedeDTO sede) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sedeService.save(sede));
    }

    @PutMapping("/{id}")//http://localhost:8080/api/sede/id
    public ResponseEntity<?> update(@Valid @RequestBody SedeDTO sede, @PathVariable Long id) {
        return sedeService.update(id, sede)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>("Sede no encontrada", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")//http://localhost:8080/api/sede/id
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<String> sede = sedeService.delete(id);
        return sede.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
