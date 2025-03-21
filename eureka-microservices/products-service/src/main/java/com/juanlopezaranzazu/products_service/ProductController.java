package com.juanlopezaranzazu.products_service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final List<Product> PRODUCTS = List.of(
            new Product(1L, "Laptop", 1200.00),
            new Product(2L, "Smartphone", 800.00),
            new Product(3L, "Laptop ASUS", 1200.00),
            new Product(4L, "Smartphone Samsung", 800.00)
    );

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return PRODUCTS.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "Laptop", 1500.0),
                new Product(2L, "Mouse", 25.0),
                new Product(3L, "Laptop ASUS", 1200.00),
                new Product(4L, "Smartphone Samsung", 800.00)
        );
    }
}
