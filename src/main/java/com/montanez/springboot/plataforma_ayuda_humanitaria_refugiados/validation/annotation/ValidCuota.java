package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.annotation;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.validation.validador.CuotaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CuotaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCuota {
    String message() default "Cuota no válida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
