package com.api.springbootexceptionhandling.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8080/?message=Daniel
    // http://localhost:8080/?message=IllegalArgument
    // http://localhost:8080/?message=IOException
    // http://localhost:8080/?message=Custom
    @GetMapping("/")
    public String getMessage(String message) throws IOException, CustomException{

        if (message.equalsIgnoreCase("IllegalArgument")) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        if (message.equalsIgnoreCase("IOException")) {
            throw new IOException("IO Exception");
        }
        if (message.equalsIgnoreCase("Custom")) {
            throw new CustomException("My custom error message");
        }

        return "Hello " + message;
    }

}
