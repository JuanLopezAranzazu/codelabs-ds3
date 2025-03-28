package com.juanlopezaranzazu.api_rest_crud.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("El producto con ID " + id + " no fue encontrado.");
    }
}