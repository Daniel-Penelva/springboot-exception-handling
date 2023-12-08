package com.api.springbootexceptionhandling.exception;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.springbootexceptionhandling.controller.CustomException;

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

    @ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse errorHandlerIO(IOException ex, WebRequest req) {

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeIO");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = { CustomException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse errorHandlerCustom(CustomException ex, WebRequest req) {

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeCustom");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse errorHandlerExc(CustomException ex, WebRequest req) {

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeException");
        err.setTime(new Date());
        return err;
    }

}
