package com.juanlopezaranzazu.api_rest_crud_tests.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("El usuario con ID " + id + " no fue encontrado.");
    }
}
