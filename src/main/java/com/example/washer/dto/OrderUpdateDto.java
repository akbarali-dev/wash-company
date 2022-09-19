package com.example.washer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDto {
    private UUID id;
    private Integer price;
    private String carModel;
    private String carNumber;
    private List<UUID> services;
    private List<UUID> washers;
    private Integer clientNumber;
    private Boolean isActive;

}
