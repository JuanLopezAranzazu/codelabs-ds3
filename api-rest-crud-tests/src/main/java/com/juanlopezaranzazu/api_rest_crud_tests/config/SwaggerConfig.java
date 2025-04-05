package com.juanlopezaranzazu.api_rest_crud_tests.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    // Configuración de Swagger para la API REST
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mi API con Swagger") // Título de la API
                        .version("1.0.0") // Versión de la API
                        .description("Documentación de mi API")); // Descripción de la API
    }
}

