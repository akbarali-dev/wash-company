package com.example.washer.controller;

import com.example.washer.dto.UserDto;
import com.example.washer.payload.ApiResponse;
import com.example.washer.security.JWTProvider;
import com.example.washer.service.AnswerService;
import com.example.washer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    AnswerService answerService;

    @GetMapping("/admin")
    public HttpEntity<ApiResponse> admin() {
        return answerService.answer("admin", true, null, HttpStatus.OK);
    }

    @GetMapping("/super")
    public HttpEntity<ApiResponse> superA() {
        return answerService.answer("super", true, null, HttpStatus.OK);
    }
    @GetMapping("/user")
    public HttpEntity<ApiResponse> user() {
        return answerService.answer("user", true, null, HttpStatus.OK);
    }

    @GetMapping("/no")
    public HttpEntity<ApiResponse> no() {
        return answerService.answer("no", true, null, HttpStatus.OK);
    }

}
