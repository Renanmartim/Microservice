package com.order.Order.controller;

import com.order.Order.dto.OrderRequest;
import com.order.Order.model.Order;
import com.order.Order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import java.util.List;
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
    public ResponseEntity<Order> saveOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.saveOrder(orderRequest);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAll();
    }

    public String fallbacklmethod (OrderRequest orderRequest, RuntimeException runtimeException) {
        return "Please order after some time";
    }
}
