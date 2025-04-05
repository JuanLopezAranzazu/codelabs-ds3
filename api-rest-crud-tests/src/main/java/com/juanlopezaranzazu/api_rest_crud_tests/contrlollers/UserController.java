package com.juanlopezaranzazu.api_rest_crud_tests.contrlollers;

import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserRequest;
import com.juanlopezaranzazu.api_rest_crud_tests.dtos.UserResponse;
import com.juanlopezaranzazu.api_rest_crud_tests.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuarios", description = "Endpoints para usuarios")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    // Obtener todos los usuarios
    // Ejemplo URL /api/v1/users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener todos los usuarios", description = "Obtener todos los usuarios del sistema.")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Obtener usuarios con paginación
    // Ejemplo URL /api/v1/users/pageable?page=0&size=8
    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener los usuarios con paginación", description = "Obtener los usuarios con paginación del sistema.")
    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    // Obtener un usuario por su id
    // Ejemplo URL /api/v1/users/1
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener un usuario", description = "Obtener un usuario por su ID del sistema.")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // Crear un usuario
    // Ejemplo URL /api/v1/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un usuario", description = "Guardar un usuario en el sistema.")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.save(userRequest));
    }

    // Editar un usuario por su id
    // Ejemplo URL /api/v1/users/1
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Editar un usuario", description = "Editar un usuario por su ID en el sistema.")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    // Eliminar un usuario por su id
    // Ejemplo URL /api/v1/users/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un usuario", description = "Eliminar un usuario por su ID en el sistema.")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
