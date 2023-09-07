package com.project.Microservice.service;

import com.project.Microservice.dto.RequestProduct;
import com.project.Microservice.dto.ResponseProduct;
import com.project.Microservice.model.Product;
import com.project.Microservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceProduct {

    private final ProductRepository productRepository;

    public  void createProduct(RequestProduct requestProduct) {
        Product product = Product.builder()
                .name(requestProduct.getName())
                .description(requestProduct.getDescription())
                .price(requestProduct.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} was created", product.getId());
    }

    public List<ResponseProduct> getAllProducts() {
        List<Product> allProduct = productRepository.findAll();

        return allProduct.stream().map(this::mapToProductResponse).toList();

    }

    private ResponseProduct mapToProductResponse(Product product) {
        return ResponseProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
