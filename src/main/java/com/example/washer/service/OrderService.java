package com.example.washer.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import com.example.washer.dto.OrderSaveDto;
import com.example.washer.dto.OrderUpdateDto;
import com.example.washer.model.Order;
import com.example.washer.model.Service;
import com.example.washer.model.WashCompany;
import com.example.washer.model.Washer;
import com.example.washer.payload.ApiResponse;
import com.example.washer.projection.OrderProjection;
import com.example.washer.repository.OrderRepository;
import com.example.washer.repository.ServiceRepository;
import com.example.washer.repository.WashCompanyRepository;
import com.example.washer.repository.WasherRepository;

import java.time.LocalDate;
import java.util.*;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AnswerService answerService;
    private final WasherRepository washerRepository;
    private final ServiceRepository serviceRepository;
    private final WashCompanyRepository washCompanyRepository;
    private final ModelMapper modelMapper;

    public HttpEntity<ApiResponse> getAllOrderByWashCompanyId(UUID washCompanyId, int page, int size, String dateFromStr, boolean isActive, String dateToStr) {
        Pageable pageable = PageRequest.of(page - 1, size);
        LocalDate dateTo;
        if (dateToStr.equals("2000-01-01"))
            dateTo = LocalDate.now();
        else
            dateTo = LocalDate.parse(dateToStr);

        LocalDate dateFrom = LocalDate.parse(dateFromStr);

        Page<OrderProjection> orders = orderRepository.getAllOrderByCompanyId(washCompanyId, pageable, dateFrom, dateTo, isActive);
        return answerService.answer("success", true, orders, HttpStatus.OK);
    }

    public HttpEntity<ApiResponse> getById(UUID id) {
        final Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return answerService.answer("success", true, byId.get(), HttpStatus.OK);
        }
        return answerService.answer("Not found", false, null, HttpStatus.NOT_FOUND);
    }

    public HttpEntity<ApiResponse> saveNewOrder(OrderSaveDto orderSaveDto, Errors errors, UUID washCompanyId) {
        orderSaveDto.setId(null);
        final ResponseEntity<ApiResponse> error = answerService.getError(errors);
        if (error != null) return error;

        List<UUID> washers = orderSaveDto.getWashers();
        Set<Washer> washersList = new HashSet<>();
        if (checkWasher(washers, washersList))
            return answerService.answer("Washer not found", false, null, HttpStatus.NOT_FOUND);

        List<UUID> services = orderSaveDto.getServices();
        Set<Service> serviceList = new HashSet<>();
        if (checkService(services, serviceList))
            return answerService.answer("service not found", false, null, HttpStatus.NOT_FOUND);


        final Optional<WashCompany> washCompany = washCompanyRepository.findById(washCompanyId);
        if (!washCompany.isPresent())
            return answerService.answer("wash company not found", false, null, HttpStatus.NOT_FOUND);
        Order order = modelMapper.map(orderSaveDto, Order.class);
        order.setServices(serviceList);
        order.setWashers(washersList);
        order.setWashCompany(washCompany.get());
        orderRepository.save(order);
        return answerService.answer("success", true, null, HttpStatus.OK);
    }

    private boolean checkService(List<UUID> services, Set<Service> serviceList) {
        for (UUID serviceId : services) {
            final Optional<Service> service = serviceRepository.findById(serviceId);
            if (!service.isPresent()) {
                return true;
            }
            serviceList.add(service.get());
        }
        return false;
    }

    private boolean checkWasher(List<UUID> washers, Set<Washer> washersList) {
        for (UUID washerId : washers) {
            final Optional<Washer> washer = washerRepository.findById(washerId);
            if (!washer.isPresent()) {
                return true;
            }
            washersList.add(washer.get());
        }
        return false;
    }

//    public <K> String javaArrayCastDbArray (List<K> list){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("{");
//        for (K data : list) {
//            stringBuilder.append("\"").append(data).append("\"");
//        }
//        stringBuilder.append("}");
//
//        return String.valueOf(stringBuilder);
//    }

    public <K> List<String> javaArrayCastDbArray(List<K> list) {
        List<String> list1 = new ArrayList<>();
        list1.add("}");
        for (K data : list) {
            list1.add("\"");
            list1.add(String.valueOf(data));
            list1.add("\"");

        }
        list1.add("}");


        return list1;
    }

    public HttpEntity<ApiResponse> updateOrder(OrderUpdateDto orderUpdateDto, Errors errors, UUID washCompanyId) {

        final ResponseEntity<ApiResponse> error = answerService.getError(errors);
        if (error != null)
            return error;

        final Optional<Order> byId = orderRepository.findById(orderUpdateDto.getId());
        if (!byId.isPresent()) {
            return answerService.answer("order not found", true, null, HttpStatus.NOT_FOUND);
        }
        Order order = byId.get();
        order.setId(orderUpdateDto.getId());
        if (orderUpdateDto.getWashers() != null) {
            List<UUID> washers = orderUpdateDto.getWashers();
            Set<Washer> washersList = new HashSet<>();
            if (checkWasher(washers, washersList))
                return answerService.answer("Washer not found", false, null, HttpStatus.NOT_FOUND);
            order.setWashers(washersList);
        }
        if (orderUpdateDto.getServices() != null) {
            List<UUID> services = orderUpdateDto.getServices();
            Set<Service> serviceList = new HashSet<>();
            if (checkService(services, serviceList))
                return answerService.answer("service not found", false, null, HttpStatus.NOT_FOUND);
            order.setServices(serviceList);
        }

        if (orderUpdateDto.getPrice() != null) {
            order.setPrice(orderUpdateDto.getPrice());
        }
        if (orderUpdateDto.getCarModel() != null) {
            order.setCarModel(orderUpdateDto.getCarModel());
        }
        if (orderUpdateDto.getCarNumber() != null) {
            order.setCarNumber(order.getCarNumber());
        }
        if (orderUpdateDto.getClientNumber() != null) {
            order.setClientNumber(orderUpdateDto.getClientNumber());
        }
        if (orderUpdateDto.getIsActive() != null) {
            order.setActive(order.isActive());
        }

        try {
            orderRepository.save(order);
            return answerService.answer("success", true, null, HttpStatus.OK);
        } catch (Exception e) {
            return answerService.answer("ERROR", false, null, HttpStatus.CONFLICT);
        }
    }
}
