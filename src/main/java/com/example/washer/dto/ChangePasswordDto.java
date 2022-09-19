package com.example.washer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordDto {
    @NotNull
    @NotBlank
    String oldPassword;
    @NotNull
    @NotBlank
    String newPassword;
}
