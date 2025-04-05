package com.juanlopezaranzazu.api_rest_crud_tests.services;

import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    List<UserResponse> findAll(); // Obtener todos los usuarios sin paginación
    Page<UserResponse> findAll(Pageable pageable); // Obtener todos los usuarios con paginación
    UserResponse findById(Long id); // Obtener un usuario por su ID
    UserResponse save(UserRequest userRequest); // Guardar un nuevo usuario
    UserResponse update(Long id, UserRequest userRequest); // Actualizar un usuario existente
    void delete(Long id); // Eliminar un usuario por su ID
}
