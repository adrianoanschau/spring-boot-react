package com.anschau.adriano;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private String type;
    private T data;
    private String message;

    private ApiResponse(String type, T data) {
        this.type = type;
        this.data = data;
        this.message = null;
    }

    private ApiResponse(String type, String message) {
        this.type = type;
        this.message = message;
        this.data = null;
    }

    public static <T> ApiResponse<T> build(String type, T data) {
        return new ApiResponse<T>(type, data);
    }

    public static <T> ApiResponse<T> build(String type, String message) {
        return new ApiResponse<T>(type, message);
    }
}
