package com.juanlopezaranzazu.api_rest_crud_tests.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("El usuario con correo '" + email + "' ya existe.");
    }
}
