package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RefugioDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String nombre;
    private String ubicacion;

}
