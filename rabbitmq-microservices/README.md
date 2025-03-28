# Spring Boot Comunicación entre microservicios con RabbitMQ

Ejemplo de comunicación entre microservicio con RabbitMQ y Spring Boot.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot
- RabbitMQ
- Lombok

## Pasos para ejecutar el proyecto

1. **Iniciar RabbitMQ**

  Asegúrate de tener Docker instalado. Luego, ejecuta el siguiente comando para iniciar RabbitMQ:

  ```sh
  docker compose up -d
  ```

2. **Ejecutar el servicio `orders-service`**

  Navega al directorio del servicio de órdenes y ejecuta los siguientes comandos:

  ```sh
  cd orders-service
  mvn clean install
  mvn spring-boot:run
  ```

3. **Ejecutar el servicio `products-service`**

  Navega al directorio del servicio de productos y ejecuta los siguientes comandos:

  ```sh
  cd products-service
  mvn clean install
  mvn spring-boot:run
  ```
