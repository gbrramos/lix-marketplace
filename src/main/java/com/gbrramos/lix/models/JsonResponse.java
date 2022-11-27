package com.gbrramos.lix.models;

import java.util.Date;

public class JsonResponse {
    private String message;
    private long status;
    private Object response;
    private Date timestamp;

    public JsonResponse(String message, long status, Object response) {
        super();
        this.message = message;
        this.status = status;
        this.response = response;
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    

}
