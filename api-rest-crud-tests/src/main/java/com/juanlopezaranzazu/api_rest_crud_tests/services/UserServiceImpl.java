package com.juanlopezaranzazu.api_rest_crud_tests.services;

import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserResponse;
import com.juanlopezaranzazu.api_rest_crud_tests.exceptions.UserAlreadyExistsException;
import com.juanlopezaranzazu.api_rest_crud_tests.exceptions.UserNotFoundException;
import com.juanlopezaranzazu.api_rest_crud_tests.mappers.UserMapper;
import com.juanlopezaranzazu.api_rest_crud_tests.repositories.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(IUserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        // Obtener todos los usuarios sin paginación
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(Pageable pageable) {
        // Obtener todos los usuarios con paginación
        return userRepository.findAll(pageable)
                .map(userMapper::toUserResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        // Obtener un usuario por su ID
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public UserResponse save(UserRequest userRequest) {
        // Verificar si el correo electrónico ya existe
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(userRequest.getEmail());
        }

        // Guardar un nuevo usuario
        var user = userMapper.toUser(userRequest);
        var savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest userRequest) {
        // Verificar si el usuario existe
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Verificar si el correo electrónico ya existe y no pertenece al usuario actual
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent() &&
                !user.getEmail().equals(userRequest.getEmail())) {
            throw new UserAlreadyExistsException(userRequest.getEmail());
        }

        // Actualizar el usuario existente
        userMapper.updateUserFromRequest(userRequest, user);
        var updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Verificar si el usuario existe
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        // Eliminar el usuario
        userRepository.deleteById(id);
    }
}
