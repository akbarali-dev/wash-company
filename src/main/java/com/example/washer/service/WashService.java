package com.example.washer.service;

import com.example.washer.model.Washer;
import com.example.washer.payload.ApiResponse;
import com.example.washer.projection.OrderProjection;
import com.example.washer.projection.WasherProjection;
import com.example.washer.repository.WasherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WashService {
    private final WasherRepository washerRepository;
    private final AnswerService answerService;

    public HttpEntity<ApiResponse> getById(UUID id) {
        final Optional<Washer> byId = washerRepository.findById(id);
        if (byId.isPresent()) {
            return answerService.answer("success", true, byId.get(), HttpStatus.OK);
        }
        return answerService.answer("Not found", false, null, HttpStatus.NOT_FOUND);

    }

    public HttpEntity<ApiResponse> getAllWashCompanyId(UUID washCompanyId, int page, int size, String searchName) {
        Pageable pageable = PageRequest.of(page - 1, size);
        final Page<WasherProjection> allWasherByName = washerRepository.getAllWasherByNameAndWashCompanyId(pageable, washCompanyId, searchName);
        if (allWasherByName.getTotalElements() == 0)
            return answerService.answer("no content", false, null, HttpStatus.NO_CONTENT);
        return answerService.answer("success", true, allWasherByName, HttpStatus.OK);
    }

    public HttpEntity<ApiResponse> getAllOrderByWasherId(UUID washerId, int page, int size, String dateFromStr, boolean isActive, String dateToStr) {
        Pageable pageable = PageRequest.of(page - 1, size);
        LocalDate dateTo;
        if (dateToStr == null)
            dateTo = LocalDate.now();
        else
            dateTo = LocalDate.parse(dateToStr);
        LocalDate dateFrom = LocalDate.parse(dateFromStr);

        Page<OrderProjection> getAllOrderByWasherId = washerRepository.getAllOrderByWasherId(pageable, dateFrom, dateTo, washerId);
        if (getAllOrderByWasherId.getTotalElements()==0)
            return answerService.answer("no content", false, null, HttpStatus.NO_CONTENT);

        return answerService.answer("success", true, getAllOrderByWasherId, HttpStatus.OK);
    }
}
