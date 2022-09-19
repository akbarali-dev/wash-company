package com.example.washer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import com.example.washer.payload.ApiResponse;
import com.example.washer.service.WashService;

import java.util.UUID;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WasherController {
    private final WashService washService;

    @GetMapping("/washer")
    public HttpEntity<ApiResponse> getById(@RequestParam(name = "id") UUID id) {
        return washService.getById(id);
    }

    @GetMapping("/{washCompanyId}/washers")
    public HttpEntity<ApiResponse> getAllOrderByWashCompanyId(
            @PathVariable UUID washCompanyId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "searchName", defaultValue = "") String searchName) {
        return washService.getAllWashCompanyId(washCompanyId, page, size, searchName);
    }

    @GetMapping("/{washerId}/getOrders")
    public HttpEntity<ApiResponse> getAllOrderByWashCompanyId(
            @PathVariable UUID washerId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "isActive", defaultValue = "true") boolean isActive,
            @RequestParam(name = "dateFrom", defaultValue = "2000-01-01") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo) {
        return washService.getAllOrderByWasherId(washerId, page, size, dateFrom, isActive, dateTo);
    }

}
