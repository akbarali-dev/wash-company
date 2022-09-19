package com.example.washer.common;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Component
public class Component {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
