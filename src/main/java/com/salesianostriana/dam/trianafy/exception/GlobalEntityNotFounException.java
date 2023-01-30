package com.salesianostriana.dam.trianafy.exception;

import javax.persistence.EntityNotFoundException;

public class GlobalEntityNotFounException extends EntityNotFoundException {

    public GlobalEntityNotFounException(String message, Long id) {
        super(String.format(message, id));
    }


}
