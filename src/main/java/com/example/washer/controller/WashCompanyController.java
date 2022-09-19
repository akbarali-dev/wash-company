package com.example.washer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.washer.dto.WashCompanyDto;
import com.example.washer.payload.ApiResponse;
import com.example.washer.service.WashCompanyService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WashCompanyController {

    private final WashCompanyService washCompanyService;


    @PostMapping("/washCompanyInsert")
    public HttpEntity<ApiResponse> saveNewWashCompany(@RequestPart("washCompany") WashCompanyDto washCompanyDto,
                                                      @RequestPart("avatar")MultipartFile avatar) {
        return washCompanyService.saveNewWashCompany(washCompanyDto, avatar);
    }
}
