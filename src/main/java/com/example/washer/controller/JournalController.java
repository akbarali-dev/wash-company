package com.example.washer.controller;

import com.example.washer.payload.ApiResponse;
import com.example.washer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class JournalController {
    private final AnswerService answerService;
    @GetMapping("/{washCompanyId}/getJournals")
    HttpEntity<ApiResponse> getJournalsByWashCompanyId(@PathVariable UUID washCompanyId) {
        return answerService.answer("success", true, null, HttpStatus.OK);
    }
}
