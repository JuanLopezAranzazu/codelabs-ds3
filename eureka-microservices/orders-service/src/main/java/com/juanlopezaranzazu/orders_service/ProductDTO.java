package com.juanlopezaranzazu.orders_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", precio=" + price +
                '}';
    }
}
