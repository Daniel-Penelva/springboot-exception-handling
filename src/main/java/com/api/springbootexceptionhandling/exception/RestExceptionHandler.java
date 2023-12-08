package com.api.springbootexceptionhandling.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse errorHandler(IllegalArgumentException ex, WebRequest req) {

        String responseString = "Illegal Argument";

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeIA");
        err.setTime(new Date());
        return err;
    }

}
