package com.said.estatistica_transacao.controllers.exceptions;

import java.time.OffsetDateTime;

public class StandardError {

    private OffsetDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(){
    }

    public StandardError(OffsetDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
