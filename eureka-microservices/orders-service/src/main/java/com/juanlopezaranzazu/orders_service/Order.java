package com.juanlopezaranzazu.orders_service;

public record Order(String id, ProductDTO productDTO, int quantity) {
}
