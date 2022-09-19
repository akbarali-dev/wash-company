package com.example.washer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import com.example.washer.payload.ApiResponse;
import com.example.washer.service.AnalyticsService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/{washCompanyId}/analytics")
    HttpEntity<ApiResponse> analytics(@PathVariable UUID washCompanyId,
                                      @RequestParam(name = "dateFrom", defaultValue = "2000-01-01") String dateFrom,
                                      @RequestParam(name = "dateTo") String dateTo) {
        return analyticsService.analytics(washCompanyId, dateFrom, dateTo);
    }
}
