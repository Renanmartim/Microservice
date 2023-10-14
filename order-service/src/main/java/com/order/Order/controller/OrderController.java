package com.order.Order.controller;

import com.order.Order.dto.OrderRequest;
import com.order.Order.model.Order;
import com.order.Order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbacklmethod")
    public CompletableFuture<String> saveOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return orderService.saveOrder(orderRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAll();
    }

    public CompletableFuture<String> fallbacklmethod (OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Please order after some time");
    }
}
