package com.spring.app.payload;

import java.time.LocalDateTime;

public class SuccessResponse<T> {
    private LocalDateTime timestamp;
    private boolean success;
    private int status;
    private String message;
    private T data;

    public SuccessResponse() {
    }

    public SuccessResponse(LocalDateTime timestamp, boolean success, int status, String message, T data) {
        this.timestamp = timestamp;
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
