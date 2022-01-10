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

    public ApiResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ApiResponse<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public T getResult() {
        return result;
    }

    public ApiResponse<T> result(T result) {
        this.result = result;
        return this;
    }
}
