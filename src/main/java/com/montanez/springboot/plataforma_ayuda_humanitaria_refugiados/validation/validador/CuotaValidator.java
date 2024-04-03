package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.validador;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities.Cuota;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.annotation.ValidCuota;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CuotaValidator implements ConstraintValidator<ValidCuota, Cuota> {
    @Override
    public boolean isValid(Cuota value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (Cuota cuota : Cuota.values()) {
            if (cuota.name().equals(value.name())) {
                return true;
            }
        }

        return false;
    }
}
