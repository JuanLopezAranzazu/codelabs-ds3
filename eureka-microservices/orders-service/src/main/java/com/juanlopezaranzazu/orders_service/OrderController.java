package com.juanlopezaranzazu.orders_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ProductClient productoClient;

    @Autowired
    public OrderController(ProductClient productoClient) {
        this.productoClient = productoClient;
    }

    @GetMapping("/create")
    public ResponseEntity<?> createOrder() {
        List<ProductDTO> productos = productoClient.getAllProducts();
        return ResponseEntity.ok("Pedido creado con productos: " + productos);
    }
}
