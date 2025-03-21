package com.juanlopezaranzazu.orders_service;

public record OrderRequest(Long productId, int quantity) {
}
