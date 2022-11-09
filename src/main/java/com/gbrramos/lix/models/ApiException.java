package com.gbrramos.lix.models;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiException {

    private String message;
    private HttpStatus statusCode;
    private String debugString;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private ApiException() {
        this.timestamp = LocalDateTime.now();
    }

    ApiException(HttpStatus statusCode) {
        this();
        this.statusCode = statusCode;
    }

    ApiException(HttpStatus statusCode, Throwable ex) {
        this();
        this.statusCode = statusCode;
        this.message = "Unexpected error";
        this.debugString = ex.getLocalizedMessage();
    }

    ApiException(HttpStatus statusCode, String message, Throwable ex) {
        this();
        this.statusCode = statusCode;
        this.message = message; 
        this.debugString = ex.getLocalizedMessage();
    }

    
 
    
}
