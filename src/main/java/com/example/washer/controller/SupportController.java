package com.example.washer.controller;

import com.example.washer.dto.EmailDto;
import com.example.washer.payload.ApiResponse;
import com.example.washer.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sendMessage")
public class SupportController {
    private final SupportService supportService;
    @PostMapping
    HttpEntity<ApiResponse> sendMessage(@Valid @RequestBody EmailDto emailDto, Errors errors) {
        return supportService.sendEmail(emailDto, errors);
    }
}
