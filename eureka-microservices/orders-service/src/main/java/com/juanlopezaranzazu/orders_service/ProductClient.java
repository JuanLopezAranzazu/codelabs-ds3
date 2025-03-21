package com.juanlopezaranzazu.orders_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductClient {
    @GetMapping("/products/products")
    List<ProductDTO> getAllProducts();
}
