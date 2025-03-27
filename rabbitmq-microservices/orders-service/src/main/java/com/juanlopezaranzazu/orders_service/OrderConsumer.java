package com.juanlopezaranzazu.orders_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void getProductList(List<ProductDTO> products) {
        System.out.println("Pedido recibido con los siguientes productos: " + products);
        // Aquí podrías guardar en DB, calcular totales, etc.
    }
}
