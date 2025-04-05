package com.juanlopezaranzazu.api_rest_crud_tests.repositories;

import com.juanlopezaranzazu.api_rest_crud_tests.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Obtener usuario por correo
}
