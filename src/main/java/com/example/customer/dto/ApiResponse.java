package com.example.customer.dto;

import java.time.Instant;

public record ApiResponse<T>(
        boolean success,
        T data,
        ErrorDetail error,
        String message,
        Instant timestamp
) {

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, null, message, Instant.now());
    }

    public static <T> ApiResponse<T> error(ErrorDetail error, String message) {
        return new ApiResponse<>(false, null, error, message, Instant.now());
    }
}
