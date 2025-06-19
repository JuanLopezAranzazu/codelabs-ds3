# Codelab Clean Architecture en microservicios Spring Boot

## 1. ¿Cuál es el propósito principal de Clean Architecture en el desarrollo de software?
Separar claramente las responsabilidades para facilitar el mantenimiento, prueba y evolución del software.

## 2. ¿Qué beneficios aporta Clean Architecture a un microservicio en Spring Boot?
Desacopla las capas, mejora la testabilidad, permite sustituir tecnologías fácilmente y facilita la escalabilidad.

## 3. ¿Cuáles son las principales capas de Clean Architecture y qué responsabilidad tiene cada una?
- **Domain:** contiene las entidades y reglas del negocio puras.
- **Application:** implementa los casos de uso y orquesta la lógica de negocio.
- **Infrastructure:** implementa detalles técnicos como base de datos, servicios externos, etc.
- **Delivery:** gestiona la entrada/salida del sistema (por ejemplo, controladores REST).

## 4. ¿Por qué se recomienda desacoplar la lógica de negocio de la infraestructura en un microservicio?
Para que la lógica del negocio no dependa de detalles técnicos y sea reutilizable, mantenible y testeable.

## 5. ¿Cuál es el rol de la capa de aplicación y qué tipo de lógica debería contener?
Coordinar los casos de uso del negocio, sin contener reglas de negocio ni detalles técnicos.

## 6. ¿Qué diferencia hay entre un UseCase y un Service en Clean Architecture?
Un **UseCase** representa una acción del negocio; un **Service** puede ser una agrupación de lógica que da soporte a varios use cases.

## 7. ¿Por qué se recomienda definir Repositories como interfaces en la capa de dominio en lugar de usar directamente JpaRepository?
Para desacoplar la lógica del negocio de la tecnología de persistencia, facilitando cambios y pruebas.

## 8. ¿Cómo se implementa un UseCase en un microservicio con Spring Boot y qué ventajas tiene?
Se crea una clase en la capa de aplicación que implementa una acción de negocio específica. Ventajas: claridad, pruebas unitarias más fáciles, y separación de preocupaciones.

## 9. ¿Qué problemas podrían surgir si no aplicamos Clean Architecture en un proyecto de microservicios?
Código acoplado, difícil de probar, de mantener, con alta dependencia tecnológica y baja reusabilidad.

## 10. ¿Cómo Clean Architecture facilita la escalabilidad y mantenibilidad en un entorno basado en microservicios?
Permite escalar servicios de forma independiente y mantener un código limpio, modular y fácil de extender.

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

## Resultados

### Levantar contenedores
![Levantar Contenedores](/clean-arch-springboot-ms/images/levantar-contenedores.png)

### Listar contenedores
![Listar Contenedores](/clean-arch-springboot-ms/images/listar-contenedores.png)

### Obtener productos
![Obtener Productos](/clean-arch-springboot-ms/images/obtener-productos.png)

### Obtener productos por id
![Obtener Productos ID](/clean-arch-springboot-ms/images/obtener-producto-id.png)

### Detener contenedores
![Detener Contenedores](/clean-arch-springboot-ms/images/detener-contenedores.png)