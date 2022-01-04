package com.example.demo;

public class ApiResponse<T> {

    private boolean success;

    private String message;

    private  T result;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
