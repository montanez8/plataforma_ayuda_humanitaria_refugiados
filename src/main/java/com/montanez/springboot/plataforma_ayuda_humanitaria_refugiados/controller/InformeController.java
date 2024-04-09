package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeEnvioMaterialDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeEvoluntarioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSedeDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSocioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeVoluntarioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service.InformeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_AUXILIAR')")
@RequestMapping("/api/informes")
@AllArgsConstructor
public class InformeController {
    private InformeService informeService;

    @GetMapping("/socios")
    @Operation(summary = "informe socios", description = "Informe de los socios y sus datos")
    public ResponseEntity<List<InformeSocioDto>> obtenerInformeSocios() {
        List<InformeSocioDto> informe = informeService.informeSocios();
        return ResponseEntity.ok(informe);
    }

    @GetMapping("/sedes")
    @Operation(summary = "Informe Sede", description = "Informe de las sedes y el nombre de su director")
    public ResponseEntity<List<InformeSedeDto>> obtenerInformeSedes() {
        List<InformeSedeDto> informe = informeService.informeSedes();
        return ResponseEntity.ok(informe);

    }

    @GetMapping("/voluntarios")
    @Operation(summary = "Informe Voluntarios", description = "Informe de los voluntarios y sus datos")
    public ResponseEntity<List<InformeVoluntarioDto>> obtenerInformeVoluntarios() {
        List<InformeVoluntarioDto> informe = informeService.informeVoluntarios();
        return ResponseEntity.ok(informe);
    }

    @GetMapping("/materiales")
    @Operation(summary = "Informe Materiales", description = "Informe de ayuda Material , Cantidad, Peso")
    public ResponseEntity<List<InformeEnvioMaterialDto>> obtenerInformeMateriales() {
        List<InformeEnvioMaterialDto> informe = informeService.informeMaterial();
        return ResponseEntity.ok(informe);
    }

    @GetMapping("/envios")
    @Operation(summary = "Informe Envios", description = "Informe de los envios y sus datos (destino , fecha envio , voluntarios)")
    public ResponseEntity<List<InformeEvoluntarioDto>> obtenerInformeEnvios() {
        List<InformeEvoluntarioDto> informe = informeService.informeEvoluntarios();
        return ResponseEntity.ok(informe);
    }

}
