package com.salesianostriana.dam.trianafy.exception;

import javax.persistence.EntityNotFoundException;

public class GlobalEntityListNotFoundException extends EntityNotFoundException {

    public GlobalEntityListNotFoundException(String message) {
        super(String.format(message));
    }
}
