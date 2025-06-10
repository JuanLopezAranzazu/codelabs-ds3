package com.juanlopezaranzazu.clean_arch_springboot_ms.infrastructure.persistence;

import com.juanlopezaranzazu.clean_arch_springboot_ms.domain.model.Product;
import com.juanlopezaranzazu.clean_arch_springboot_ms.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return jpaRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
