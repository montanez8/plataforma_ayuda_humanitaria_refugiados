package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    List<Socio> findByTipoCuota(Cuota tipoCuota);

}
