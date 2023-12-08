package com.api.springbootexceptionhandling.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8080/?message=Daniel
    // http://localhost:8080/?message=IllegalArgument
    @GetMapping("/")
    public String getMessage(String message) {

        if (message.equalsIgnoreCase("IllegalArgument")) {
            throw new IllegalArgumentException("Illegal Argument");
        }

        return "Hello " + message;
    }

}
