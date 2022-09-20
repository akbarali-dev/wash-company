package com.example.washer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WashCompanyDto {
    @NotBlank

    private String name;
    @NotBlank

    private String location;
}

/*

"name" : "test",
"loacation" : "location test"
 */
