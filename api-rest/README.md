# Codelab API REST en Spring Boot

## 1. ¿Cuál es el propósito de Spring Boot y por qué es útil en el desarrollo de aplicaciones Java?
Spring Boot simplifica el desarrollo de aplicaciones Java al eliminar configuraciones manuales. Ofrece un enfoque opinionado, autoconfiguración, y un servidor embebido para ejecutar la app fácilmente con `java -jar`.

## 2. ¿Cómo se configura una base de datos PostgreSQL en un proyecto Spring Boot usando `application.properties`?
```properties
# Configuracion de PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuracion de JPA
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 3. ¿Qué hace la anotación @Entity en la clase Pais y por qué es necesaria?
La anotación `@Entity` marca la clase Pais como una entidad JPA que será mapeada a una tabla en la base de datos. Es necesaria para que Hibernate gestione su persistencia.

## 4. ¿Cuál es la función de JpaRepository y por qué se usa en la capa de persistencia?
`JpaRepository` proporciona métodos CRUD listos para usar. Se utiliza para acceder a la base de datos sin escribir código SQL o implementar repositorios manualmente.

## 5. ¿Cómo se implementa la inyección de dependencias en el servicio PaisService y por qué es importante?
Usando @Autowired o constructor injection:

```java
// Servicio
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
}
```
Es importante porque desacopla componentes y facilita pruebas y mantenimiento.

## 6. ¿Cuál es la diferencia entre `@RestController` y `@Service` en Spring Boot?
* `@RestController`: define un controlador REST que maneja solicitudes HTTP.
* `@Service`: marca una clase de lógica de negocio o servicio, usada por los controladores.

## 7. ¿Cómo se define un endpoint en un controlador REST para buscar un país por su nombre?
```java
// Controlador
@GetMapping("/name/{name}")
public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
    return countryService.getCountryByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
```

## 8. ¿Por qué es útil Docker para ejecutar PostgreSQL en lugar de instalarlo manualmente?
Docker permite levantar PostgreSQL rápidamente en un contenedor aislado, sin afectar el sistema local, con configuración reproducible y portable.

## 9. ¿Cómo se ejecuta y prueba la API REST en IntelliJ IDEA?
1. Ejecutar la clase principal (`@SpringBootApplication`).
2. Usar herramientas como Postman o el plugin HTTP de IntelliJ para probar endpoints REST.
3. Consultar en `http://localhost:8080`.

## 10. ¿Cómo se maneja la eliminación de un país en el API y qué código de respuesta devuelve el servidor?
```java
// Servicio
public void deleteCountry(Long id) {
    countryRepository.deleteById(id);
}

// Controlador
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
    countryService.deleteCountry(id);
    return ResponseEntity.noContent().build();
}
```
Devuelve HTTP 204 No Content al eliminar exitosamente.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL Driver

## Construir y ejecutar
```sh
mvn clean install
mvn spring-boot:run
```

## Construir y ejecutar con Docker
```sh
docker compose up -d
```

## Detener los servicios
```sh
docker compose down
```