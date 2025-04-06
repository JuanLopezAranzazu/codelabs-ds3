package com.juanlopezaranzazu.api_rest_crud_tests.services;

import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserResponse;
import com.juanlopezaranzazu.api_rest_crud_tests.entities.User;
import com.juanlopezaranzazu.api_rest_crud_tests.exceptions.UserAlreadyExistsException;
import com.juanlopezaranzazu.api_rest_crud_tests.exceptions.UserNotFoundException;
import com.juanlopezaranzazu.api_rest_crud_tests.mappers.UserMapper;
import com.juanlopezaranzazu.api_rest_crud_tests.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnAllUsers() {
        User user = new User(1L, "Test User", "test@example.com");
        UserResponse userResponse = new UserResponse(1L, "Test User", "test@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        List<UserResponse> result = userService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test User", result.get(0).getName());
        verify(userRepository).findAll();
    }

    @Test
    void shouldReturnPagedUsers() {
        User user = new User(1L, "Test User", "test@example.com");
        UserResponse userResponse = new UserResponse(1L, "Test User", "test@example.com");

        Pageable pageable = PageRequest.of(0, 10);
        Page<User> userPage = new PageImpl<>(List.of(user));

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        Page<UserResponse> result = userService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test User", result.getContent().get(0).getName());
        verify(userRepository).findAll(pageable);
    }

    @Test
    void shouldReturnUserById() {
        Long userId = 1L;
        User user = new User(userId, "Test User", "test@example.com");
        UserResponse userResponse = new UserResponse(userId, "Test User", "test@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldSaveNewUser() {
        UserRequest userRequest = new UserRequest("Test User", "test@example.com");
        User user = new User(null, "Test User", "test@example.com");
        User savedUser = new User(1L, "Test User", "test@example.com");
        UserResponse userResponse = new UserResponse(1L, "Test User", "test@example.com");

        when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toUser(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toUserResponse(savedUser)).thenReturn(userResponse);

        UserResponse result = userService.save(userRequest);

        assertNotNull(result);
        assertEquals("Test User", result.getName());
        verify(userRepository).findByEmail(userRequest.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExistsOnSave() {
        UserRequest userRequest = new UserRequest("Test User", "test@example.com");
        User existingUser = new User(1L, "Existing User", "test@example.com");

        when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.of(existingUser));

        assertThrows(UserAlreadyExistsException.class, () -> userService.save(userRequest));
        verify(userRepository).findByEmail(userRequest.getEmail());
    }

    @Test
    void shouldUpdateExistingUser() {
        Long userId = 1L;
        UserRequest userRequest = new UserRequest("Updated User", "updated@example.com");
        User existingUser = new User(userId, "Test User", "test@example.com");
        User updatedUser = new User(userId, "Updated User", "updated@example.com");
        UserResponse userResponse = new UserResponse(userId, "Updated User", "updated@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.empty());
        // Aquí simulamos que el mapper actualiza el user existente
        doAnswer(invocation -> {
            UserRequest req = invocation.getArgument(0);
            User user = invocation.getArgument(1);
            user.setName(req.getName());
            user.setEmail(req.getEmail());
            return null;
        }).when(userMapper).updateUserFromRequest(userRequest, existingUser);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.toUserResponse(updatedUser)).thenReturn(userResponse);

        UserResponse result = userService.update(userId, userRequest);

        assertNotNull(result);
        assertEquals("Updated User", result.getName());
        verify(userRepository).findById(userId);
        verify(userRepository).save(existingUser);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExistsOnUpdate() {
        Long userId = 1L;
        UserRequest userRequest = new UserRequest("Test User", "existing@example.com");
        User existingUser = new User(userId, "Test User", "test@example.com");
        User anotherUserWithEmail = new User(2L, "Another User", "existing@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.of(anotherUserWithEmail));

        assertThrows(UserAlreadyExistsException.class, () -> userService.update(userId, userRequest));
        verify(userRepository).findById(userId);
        verify(userRepository).findByEmail(userRequest.getEmail());
    }
}

