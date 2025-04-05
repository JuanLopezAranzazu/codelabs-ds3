package com.juanlopezaranzazu.api_rest_crud_tests.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Genera automáticamente getters, setters, equals, hashCode y toString
@NoArgsConstructor  // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
@Entity  // Indica que esta clase es una entidad JPA
@Table(name = "users")  // Nombre de la tabla en la base de datos
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del usuario

    @Column(nullable = false)
    private String name; // Nombre del usuario

    @Column(nullable = false, unique = true)
    private String email; // Correo electrónico del usuario

    // Constructor con argumentos (sin id)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
