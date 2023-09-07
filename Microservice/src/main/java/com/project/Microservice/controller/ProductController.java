package com.project.Microservice.controller;

import com.project.Microservice.dto.RequestProduct;
import com.project.Microservice.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ServiceProduct serviceProduct;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody RequestProduct requestProduct) {
        serviceProduct.createProduct(requestProduct);
    }

}
