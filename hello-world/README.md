# Codelab Hola Mundo en Spring Boot

## 1. ¿Qué es Spring Boot y para qué sirve?
Spring Boot es un framework basado en Spring que facilita la creación de aplicaciones Java con configuración mínima, proporcionando un entorno listo para producción.

## 2. ¿Qué hace la anotación `@SpringBootApplication`?
Es una combinación de `@Configuration`, `@EnableAutoConfiguration` y `@ComponentScan`, que configura automáticamente la aplicación.

## 3. ¿Cómo se inicia una aplicación Spring Boot?
Ejecutando la clase principal con `main()` o usando `mvn spring-boot:run` en Maven.

## 4. ¿Qué función tiene la anotación `@RestController`?
Define una clase como un controlador REST, permitiendo manejar solicitudes HTTP y devolver respuestas en formato JSON.

## 5. ¿Cómo defines una URL en un controlador de Spring Boot?
Usando `@RequestMapping("/ruta")` o `@GetMapping("/ruta")` dentro de un `@RestController`.

## 6. ¿Cuál es el puerto por defecto en el que corre Spring Boot?
El puerto por defecto es `8080`.

## 7. ¿Cómo cambias el puerto de la aplicación?
Editando `application.properties` o `application.yml` y agregando `server.port=PUERTO`.

## 8. ¿Qué comando de Maven permite ejecutar una aplicación Spring Boot?
`mvn spring-boot:run`.

## 9. ¿Cómo puedes probar un endpoint de Spring Boot en el navegador?
Ingresando la URL del endpoint en el navegador, por ejemplo: `http://localhost:8080/hola`.

## 10. ¿Para qué sirve el archivo `application.properties`?
Para configurar parámetros de la aplicación, como el puerto, base de datos y otros ajustes.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot

## Construir y ejecutar
```sh
mvn clean install
mvn spring-boot:run
```

## Resultados

### Ejecutando microservicio
![Ejecutar Microservicio](/hello-world/images/ejecutando-microservicio.png)

### Obtener mensajes
![Obtener Mensajes](/hello-world/images/obtener-mensajes.png)

### Obtener mensajes por id
![Obtener Mensajes ID](/hello-world/images/obtener-mensaje-id.png)

### Crear mensajes
![Crear Mensajes](/hello-world/images/crear-mensaje.png)

### Actualizar mensajes
![Actualizar Mensajes](/hello-world/images/actualizar-mensaje.png)

### Eliminar mensajes
![Eliminar Mensajes](/hello-world/images/eliminar-mensaje.png)