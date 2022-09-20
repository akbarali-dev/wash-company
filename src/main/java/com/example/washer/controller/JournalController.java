package com.example.washer.controller;

import com.example.washer.dto.Change;
import com.example.washer.payload.ApiResponse;
import com.example.washer.service.AnswerService;
import com.example.washer.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class JournalController {
    private final AnswerService answerService;
    private final JournalService journalService;
    @GetMapping("/{washCompanyId}/getJournals")
    HttpEntity<ApiResponse> getJournalsByWashCompanyId(@PathVariable UUID washCompanyId,
                                                       @RequestParam(name = "page", defaultValue = "1") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        return journalService.getAllJournalsByCompanyId(washCompanyId, page, size);
    }

    @PostMapping("/{washCompanyId}/insertJournal")
    HttpEntity<ApiResponse> saveNewJournal(@Valid @RequestBody Change change, @PathVariable UUID washCompanyId, Errors errors){
        return journalService.saveNewJournal(change,washCompanyId, errors);
    }
}
