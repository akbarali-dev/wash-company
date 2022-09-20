package com.example.washer.service;

import com.example.washer.dto.Change;
import com.example.washer.payload.ApiResponse;
import com.example.washer.projection.JournalProjection;
import com.example.washer.repository.JournalRepository;
import com.example.washer.repository.OrderRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final AnswerService answerService;
    private final OrderRepository orderRepository;

    public Change allChanges(String changes) {
        Gson gson = new Gson();
        final Change change = gson.fromJson(changes, Change.class);
        return change;
    }

    public HttpEntity<ApiResponse> getAllJournalsByCompanyId(UUID washCompanyId, int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        final Page<JournalProjection> allJournalByCompanyId = journalRepository.getAllJournalByCompanyId(washCompanyId, pageable);
        final List<JournalProjection> allJournalByCompanyId = journalRepository.getAllJournalByCompanyId(washCompanyId, page, size);
        if (allJournalByCompanyId.size() == 0) {
            return answerService.answer("not found", false, null, HttpStatus.NO_CONTENT);
        }
        return answerService.answer("success", true, allJournalByCompanyId, HttpStatus.OK);
    }

    public HttpEntity<ApiResponse> saveNewJournal(Change change, UUID washCompanyId, Errors errors) {
        final ResponseEntity<ApiResponse> error = answerService.getError(errors);
        if (error!=null) {
            return error;
        }
        final UUID orderId = change.getOrderId();
        final boolean existsOrderById = orderRepository.existsByIdAndWashCompanyId(orderId, washCompanyId);
        if (!existsOrderById) {
            return answerService.answer("order not found", false, null, HttpStatus.NOT_FOUND);
        }
        Gson gson = new Gson();
        final String changeStr = gson.toJson(change);
        try {
        journalRepository.saveObjectJson(changeStr);
            return answerService.answer("success", true, null, HttpStatus.OK);
        }
        catch (Exception e){
            return answerService.answer("error", true, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
