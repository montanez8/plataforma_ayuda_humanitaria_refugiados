package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;

import java.io.IOException;

public class CuotaDeserializer extends JsonDeserializer<Cuota> {

    @Override
    public Cuota deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        try {
            return Cuota.valueOf(value);
        } catch (IllegalArgumentException e) {
            System.out.println("Cuota no válida" + e);
        }
        return null;
    }
}
