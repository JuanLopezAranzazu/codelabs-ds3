package com.juanlopezaranzazu.products_service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final AmqpTemplate customRabbitTemplate;

    public ProductService(AmqpTemplate customRabbitTemplate) {
        this.customRabbitTemplate = customRabbitTemplate;
    }

    public void sendProductList(List<ProductDTO> products) {
        // Enviamos la lista de productos en formato JSON
        customRabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                products
        );
        System.out.println("Lista de productos enviada con éxito en formato JSON.");
    }
}
