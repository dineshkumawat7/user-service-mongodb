package com.spring.app.payload;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private boolean success;
    private int status;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, boolean success, int status, String message) {
        this.timestamp = timestamp;
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
