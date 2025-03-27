package com.juanlopezaranzazu.products_service;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/send")
    public String sendProducts(@RequestBody List<ProductDTO> products) {
        productService.sendProductList(products);
        return "Productos enviados con éxito al orders-service";
    }
}
