package com.juanlopezaranzazu.api_rest_crud_tests.services;

import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserResponse;
import com.juanlopezaranzazu.api_rest_crud_tests.repositories.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public UserResponse save(UserRequest userRequest) {
        return null;
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

    }
}
