package com.salesianostriana.dam.trianafy.validation.validator;

import com.salesianostriana.dam.trianafy.service.SongService;
import com.salesianostriana.dam.trianafy.validation.annotation.UniqueSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSongValidator implements ConstraintValidator<UniqueSong, String> {
    @Autowired
    private SongService songService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty()) {
            return true;
        }
        return StringUtils.hasText(s) && !songService.songExists(s);
    }
}
