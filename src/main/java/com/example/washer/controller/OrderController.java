package com.example.washer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.example.washer.dto.OrderSaveDto;
import com.example.washer.dto.OrderUpdateDto;
import com.example.washer.payload.ApiResponse;
import com.example.washer.service.OrderService;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public HttpEntity<ApiResponse> getById(@RequestParam(name = "id") UUID id) {
        return orderService.getById(id);
    }

    @GetMapping("/{washCompanyId}/orders")
    public HttpEntity<ApiResponse> getAllOrderByWashCompanyId(
            @PathVariable UUID washCompanyId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "isActive", defaultValue = "true") boolean isActive,
            @RequestParam(name = "dateFrom", defaultValue = "2000-01-01") String dateFrom,
            @RequestParam(name = "dateTo", defaultValue = "2000-01-01") String dateTo) {
        return orderService.getAllOrderByWashCompanyId(washCompanyId, page, size, dateFrom, isActive, dateTo);
    }

    @PostMapping("/{washCompanyId}/insertOrder")
    public HttpEntity<ApiResponse> insertNewOrder(@Valid @RequestBody OrderSaveDto orderSaveDto, Errors errors, @PathVariable UUID washCompanyId) {
        return orderService.saveNewOrder(orderSaveDto, errors, washCompanyId);
    }

    @PostMapping("/{washCompanyId}/updateOrder")
    public HttpEntity<ApiResponse> updateOrder(@Valid @RequestBody OrderUpdateDto orderUpdateDto, Errors errors, @PathVariable UUID washCompanyId) {
        return orderService.updateOrder(orderUpdateDto, errors, washCompanyId);
    }


}
