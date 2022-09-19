package com.example.washer.service;

import com.example.washer.payload.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


import java.util.*;

@Service
public class AnswerService {




    public HttpEntity<ApiResponse> answer(String message, boolean isSuccess, Object data, HttpStatus status) {
        return ResponseEntity.status(
                        status)
                .body(
                        new ApiResponse(
                                message,
                                isSuccess,
                                data
                        ));
    }


    public ResponseEntity<ApiResponse> getError(Errors errors) {
        Map<String, List<String>> error = new HashMap<>();
        if (errors.hasErrors()) {
            for (FieldError fieldError : errors.getFieldErrors()) {
                if (!error.containsKey(fieldError.getField())) {
                    error.put(fieldError.getField(),
                            new ArrayList<>(Collections.singletonList(fieldError.getDefaultMessage())));
                } else {
                    error.get(fieldError.getField()).add(fieldError.getDefaultMessage());
                }
            }

            return ResponseEntity.status(
                    HttpStatus.CONFLICT
            ).body(
                    new ApiResponse(
                            "ERROR",
                            false,
                            null,
                            error
                    ));
        }
        return null;
    }






}
