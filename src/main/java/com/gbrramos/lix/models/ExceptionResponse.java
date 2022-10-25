package com.gbrramos.lix.models;

public class ExceptionResponse {
    private String message;
    private Integer statusCode;

    public ExceptionResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
}
