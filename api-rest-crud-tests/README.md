# Spring Boot API REST CRUD TESTS

Ejemplo API REST CRUD con TESTS en Spring Boot.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL Driver

### Construir y ejecutar
```sh
mvn clean install
mvn spring-boot:run
```

### Ejecución de pruebas

Para ejecutar las pruebas del proyecto, usa el siguiente comando:

```sh
mvn test
```

### Generar el archivo JAR
Para generar el archivo JAR de tu proyecto, ejecuta el siguiente comando:

```sh
mvn clean package
```

### Documentación de la API

La documentación de la API está disponible en Swagger. Una vez que el microservicio esté en ejecución, puedes acceder a ella en la siguiente URL:

[http://localhost:9090/swagger-ui/index.html](http://localhost:9090/swagger-ui/index.html)