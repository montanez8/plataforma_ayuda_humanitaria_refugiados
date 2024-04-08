package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.service;

import java.util.List;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSedeDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeSocioDto;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.dto_informes.InformeVoluntarioDto;

public interface InformeService {
    List<InformeSocioDto> informeSocios();

    List<InformeSedeDto> informeSedes();

    List<InformeVoluntarioDto> informeVoluntarios();
}
