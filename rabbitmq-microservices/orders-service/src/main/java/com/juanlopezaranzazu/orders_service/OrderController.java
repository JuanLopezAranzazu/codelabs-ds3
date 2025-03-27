package com.juanlopezaranzazu.orders_service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/create")
    public String createOrder() {
        return "El pedido está en proceso y se recibirá en cuanto llegue el mensaje asíncrono.";
    }
}
