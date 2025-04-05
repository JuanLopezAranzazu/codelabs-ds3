package com.juanlopezaranzazu.api_rest_crud_tests.mappers;

import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserResponse;
import com.juanlopezaranzazu.api_rest_crud_tests.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Método para convertir un objeto User a un objeto UserResponse
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    // Método para convertir un objeto UserRequest a un objeto User
    public User toUser(UserRequest userRequest) {
        return new User(
                userRequest.getName(),
                userRequest.getEmail()
        );
    }

    // Método para convertir un objeto UserRequest a un objeto User, pero sin el id
    public void updateUserFromRequest(UserRequest userRequest, User user) {
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
    }
}