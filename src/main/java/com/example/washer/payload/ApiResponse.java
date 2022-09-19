package com.example.washer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;

    private boolean isSuccess = false;

    private Object data;

    private Object error;

    public ApiResponse(String message, boolean isSuccess, Object data) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.data = data;
    }
}
