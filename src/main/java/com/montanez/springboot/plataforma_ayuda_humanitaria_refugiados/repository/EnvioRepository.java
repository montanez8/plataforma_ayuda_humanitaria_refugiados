package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

}
