package com.juanlopezaranzazu.api_rest_crud.services;

import com.juanlopezaranzazu.api_rest_crud.dtos.ProductRequest;
import com.juanlopezaranzazu.api_rest_crud.dtos.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll(); // Obtener todos los productos sin paginación
    Page<ProductResponse> findAll(Pageable pageable); // Obtener todos los productos con paginación
    ProductResponse findById(Long id); // Obtener un producto por su ID
    ProductResponse save(ProductRequest productRequest); // Guardar un nuevo producto
    ProductResponse update(Long id, ProductRequest productRequest); // Actualizar un producto existente
    void delete(Long id); // Eliminar un producto por su ID
}
