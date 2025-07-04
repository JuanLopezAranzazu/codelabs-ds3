# Spring Boot Comunicación entre microservicios con Eureka

Ejemplo comunicación entre microservicios con Eureka y Spring Boot.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot
- Eureka
- Lombok

### Construir y ejecutar
Para cargar las dependencias y ejecutar las aplicaciones Spring Boot, sigue estos pasos:

1. **Cargar Dependencias**:
  Ejecuta el siguiente comando en la raíz del proyecto para cargar todas las dependencias necesarias:
  ```bash
  ./mvn clean install
  ```

2. **Ejecutar Aplicaciones**:
  Navega a cada uno de los directorios de los microservicios y ejecuta el siguiente comando para iniciar cada aplicación:
  ```bash
  ./mvn spring-boot:run
  ```

  Alternativamente, puedes empaquetar las aplicaciones y ejecutarlas como archivos JAR:
  ```bash
  ./mvn clean package
  java -jar target/nombre-del-archivo.jar
  ```

## Resultados

### Ejecutando microservicio de eureka
![Ejecutando Eureka](/eureka-microservices/images/ejecutando-eureka.png)

### Ejecutando microservicio de productos
![Ejecutando Productos](/eureka-microservices/images/ejecutando-productos.png)

### Ejecutando microservicio de pedidos
![Ejecutando Pedidos](/eureka-microservices/images/ejecutando-pedidos.png)

### Registro microservicios en eureka
![Registro Eureka](/eureka-microservices/images/registro-eureka.png)

### Prueba de funcionamiento
![Prueba Funcionamiento](/eureka-microservices/images/prueba-funcionamiento.png)