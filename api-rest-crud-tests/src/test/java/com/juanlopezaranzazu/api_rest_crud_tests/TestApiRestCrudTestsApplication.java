package com.juanlopezaranzazu.api_rest_crud_tests;

import org.springframework.boot.SpringApplication;

public class TestApiRestCrudTestsApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiRestCrudTestsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
