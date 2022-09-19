package com.example.washer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.washer.payload.ApiResponse;
import com.example.washer.projection.AnalyticsProjection;
import com.example.washer.repository.OrderRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final OrderRepository orderRepository;
    private final AnswerService answerService;

    public HttpEntity<ApiResponse> analytics(UUID washCompanyId, String dateFromStr, String dateToStr) {
        LocalDate dateTo;
        if (dateToStr == null)
            dateTo = LocalDate.now();
        else
            dateTo = LocalDate.parse(dateToStr);

        LocalDate dateFrom = LocalDate.parse(dateFromStr);
        final AnalyticsProjection analytic = orderRepository.analytic(washCompanyId, dateFrom, dateTo);
        if (analytic==null) {
            return answerService.answer("no content", false, null, HttpStatus.NO_CONTENT);
        }
            return answerService.answer("success", true, analytic, HttpStatus.OK);

    }
}
