package com.bosonit.backweb.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CustomUnprocessableException extends RuntimeException {

    public CustomUnprocessableException(String message) {
        super(message);
    }
}
