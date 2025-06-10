package com.juanlopezaranzazu.clean_arch_springboot_ms.infrastructure.persistence;

import com.juanlopezaranzazu.clean_arch_springboot_ms.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
