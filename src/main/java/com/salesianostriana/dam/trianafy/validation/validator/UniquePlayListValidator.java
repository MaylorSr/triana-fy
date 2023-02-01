package com.salesianostriana.dam.trianafy.validation.validator;

import com.salesianostriana.dam.trianafy.service.PlaylistService;
import com.salesianostriana.dam.trianafy.validation.annotation.UniquePlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePlayListValidator implements ConstraintValidator<UniquePlayList, String> {
    @Autowired
    private PlaylistService playlistService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty()) {
            return true;
        }
        return StringUtils.hasText(s) && !playlistService.playListExist(s);
    }
}
