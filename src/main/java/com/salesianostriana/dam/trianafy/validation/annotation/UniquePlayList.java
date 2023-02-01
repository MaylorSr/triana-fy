package com.salesianostriana.dam.trianafy.validation.annotation;

import com.salesianostriana.dam.trianafy.validation.validator.UniqueArtistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueArtistValidator.class)
@Documented
public @interface UniquePlayList {

    String message() default "The name of the PlayList was exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
