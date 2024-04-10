package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.rabbit_mq;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto.EnvioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor
public class ShipmentCreatedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private EnvioDTO envioDTO;

}
