package com.example.washer.service;

import com.example.washer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.washer.dto.WashCompanyDto;
import com.example.washer.model.WashCompany;
import com.example.washer.payload.ApiResponse;
import com.example.washer.repository.WashCompanyRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WashCompanyService {

    private final WashCompanyRepository washCompanyRepository;
    private final AnswerService answerService;

    public HttpEntity<ApiResponse> saveNewWashCompany(WashCompanyDto washCompanyDto, MultipartFile multipartFile) {
        try {
            final byte[] avatar = multipartFile.getBytes();
            try {

                washCompanyRepository.save(new WashCompany(washCompanyDto.getName(), washCompanyDto.getLocation(), avatar));
            } catch (Exception e) {
                return answerService.answer("ERROR", false, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return answerService.answer("SUCCESS", true, null, HttpStatus.OK);
        } catch (IOException e) {
            return answerService.answer("File error", false, null, HttpStatus.CONFLICT);
//            throw new RuntimeException(e);
        }
    }
}
