package com.bosonit.backbusiness.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public final ResponseEntity<CustomErrorMessage> handleNotFoundException(CustomNotFoundException ex,
            WebRequest request) {
        CustomErrorMessage exceptionResponse = new CustomErrorMessage(new Date(), HttpStatus.NOT_FOUND.value(),
                ex.getMessage());
        return new ResponseEntity<CustomErrorMessage>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomUnprocessableException.class)
    public final ResponseEntity<CustomErrorMessage> handleUnprocessableException(CustomUnprocessableException ex,
                                                                                 WebRequest request) {
        CustomErrorMessage exceptionResponse = new CustomErrorMessage(new Date(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return new ResponseEntity<CustomErrorMessage>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
