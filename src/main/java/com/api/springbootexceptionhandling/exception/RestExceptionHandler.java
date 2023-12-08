package com.api.springbootexceptionhandling.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object> errorHandler(IllegalArgumentException ex, WebRequest req) {

        String responseString = "Illegal Argument";

        return handleExceptionInternal(ex, responseString, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

}
