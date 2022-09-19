package com.example.washer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    @NotNull
    @NotBlank
    @NotEmpty
    private String login;
    @NotNull
    @NotBlank
    @NotEmpty
    private String password;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;


}
