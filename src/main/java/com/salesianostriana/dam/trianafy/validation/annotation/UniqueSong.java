package com.salesianostriana.dam.trianafy.validation.annotation;

import com.salesianostriana.dam.trianafy.validation.validator.UniqueSongValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSongValidator.class)
@Documented
public @interface UniqueSong {

    String message() default "The name of the Song was exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
