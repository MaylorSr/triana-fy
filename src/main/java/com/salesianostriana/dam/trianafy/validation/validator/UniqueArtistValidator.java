package com.salesianostriana.dam.trianafy.validation.validator;

import com.salesianostriana.dam.trianafy.service.ArtistService;
import com.salesianostriana.dam.trianafy.validation.annotation.UniqueArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueArtistValidator implements ConstraintValidator<UniqueArtist, String> {

    @Autowired
    private ArtistService artistService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && !artistService.artistExists(s);
    }
}
