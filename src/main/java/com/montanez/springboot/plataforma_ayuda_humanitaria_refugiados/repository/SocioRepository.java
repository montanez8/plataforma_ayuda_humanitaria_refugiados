package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    List<Socio> findByTipoCuota(Cuota tipoCuota);

}
