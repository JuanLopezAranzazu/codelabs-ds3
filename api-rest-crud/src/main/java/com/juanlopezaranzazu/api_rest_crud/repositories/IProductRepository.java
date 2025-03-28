package com.juanlopezaranzazu.api_rest_crud.repositories;

import com.juanlopezaranzazu.api_rest_crud.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
