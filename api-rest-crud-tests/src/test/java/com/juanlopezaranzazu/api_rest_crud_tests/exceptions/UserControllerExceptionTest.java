package com.juanlopezaranzazu.api_rest_crud_tests.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanlopezaranzazu.api_rest_crud_tests.controllers.UserController;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerExceptionTest {

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Mock
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
        // Simula que el servicio lanza UserNotFoundException
        Long userId = 1L;
        when(userService.findById(anyLong())).thenThrow(new UserNotFoundException(userId));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("El usuario con ID " + userId + " no fue encontrado.")))
                .andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    void shouldReturnBadRequestWhenEmailAlreadyExists() throws Exception {
        UserRequest request = new UserRequest();
        request.setName("Test");
        request.setEmail("existing@example.com");

        when(userService.save(any(UserRequest.class)))
                .thenThrow(new UserAlreadyExistsException(request.getEmail()));

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("El usuario con correo '" + request.getEmail() + "' ya existe.")))
                .andExpect(jsonPath("$.status", is(400)));
    }

    @Test
    void shouldReturnBadRequestWhenValidationFails() throws Exception {
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setName(""); // Inválido: vacío
        invalidRequest.setEmail("invalid-email"); // Inválido: formato incorrecto

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors.name", is("El nombre no puede estar vacío")))
                .andExpect(jsonPath("$.errors.email", is("El correo electrónico debe tener un formato válido")));
    }

    @Test
    void shouldReturnInternalServerErrorOnUnexpectedException() throws Exception {
        when(userService.findById(anyLong())).thenThrow(new RuntimeException("Error inesperado"));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error", containsString("Error inesperado")))
                .andExpect(jsonPath("$.status", is(500)));
    }

    @Test
    void shouldReturnInternalServerErrorOnDatabaseException() throws Exception {
        when(userService.findById(anyLong())).thenThrow(new DataAccessException("Error de base de datos") {});

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error", containsString("Error de base de datos")))
                .andExpect(jsonPath("$.status", is(500)));
    }
}
