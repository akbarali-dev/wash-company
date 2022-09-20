package com.example.washer.controller;

import com.example.washer.dto.RegisterUserDto;
import com.example.washer.dto.UserDto;
import com.example.washer.payload.ApiResponse;
import com.example.washer.security.JWTProvider;
import com.example.washer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signIn")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JWTProvider jwtProvider;

    @PostMapping
    public HttpEntity<ApiResponse> login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
//        UserDetails userDetails = userService.loadUserByUsername(userDto.getUsername());
//        String generatedToken = jwtProvider.generateToken(userDetails.getUsername());
//        return ResponseEntity.ok(generatedToken);
    }

    @PostMapping("/signUp")
    public HttpEntity<ApiResponse> register(@RequestBody RegisterUserDto userDto) {
        return userService.register(userDto);
    }
}
