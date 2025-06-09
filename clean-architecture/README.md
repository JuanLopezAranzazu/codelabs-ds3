# Codelab Clean Architecture

## 1. ¿Qué problema busca resolver Clean Architecture en el desarrollo de software?
Busca crear sistemas mantenibles, escalables y desacoplados, separando la lógica de negocio de la infraestructura.

## 2. ¿Cuáles son las principales capas de Clean Architecture y qué responsabilidad tiene cada una?
1. **Entidad (Domain)**: Define las reglas de negocio.
2. **Casos de uso (Application)**: Contiene la lógica de aplicación.
3. **Adaptadores (Interface Adapters)**: Implementa controladores, presentadores y repositorios.
4. **Infraestructura (Frameworks & Drivers)**: Contiene frameworks, base de datos y detalles técnicos.

## 3. ¿Qué relación tiene Clean Architecture con el principio de Inversión de Dependencias (DIP) en SOLID?
Las dependencias apuntan hacia el dominio, evitando acoplamiento con infraestructura, aplicando DIP al depender de abstracciones.

## 4. ¿Por qué es importante desacoplar la lógica de negocio de la infraestructura en una aplicación?
Permite mayor flexibilidad, facilita pruebas unitarias y evita dependencias rígidas en frameworks o bases de datos.

## 5. ¿Cómo Clean Architecture facilita la escalabilidad y mantenibilidad de un sistema?
Al separar la lógica de negocio de la infraestructura, permite modificar componentes sin afectar otras partes del sistema.

## 6. ¿Qué diferencia hay entre la capa de dominio y la capa de aplicación en Clean Architecture?
- **Dominio**: Contiene reglas de negocio puras e independientes.
- **Aplicación**: Implementa casos de uso y coordina la interacción con la infraestructura.

## 7. ¿Por qué los controladores (controllers) y la base de datos deben estar en la capa de infraestructura?
Porque dependen de frameworks externos y Clean Architecture busca mantener la lógica de negocio independiente de estos.

## 8. ¿Qué ventajas tiene usar una interfaz en la capa de dominio para definir repositorios en lugar de usar directamente JpaRepository?
- Desacopla la lógica de negocio de la persistencia.
- Permite cambiar la tecnología de base de datos sin afectar el dominio.
- Facilita la implementación de pruebas unitarias.

## 9. ¿Cómo interactúan los casos de uso (UseCases) con las entidades de dominio en Clean Architecture?
Los casos de uso contienen la lógica de aplicación y manipulan las entidades del dominio sin depender de la infraestructura.

## 10. ¿Cómo se puede aplicar Clean Architecture en una aplicación de microservicios con Spring Boot?
- Definiendo capas claras de dominio, aplicación e infraestructura.
- Usando interfaces para repositorios y servicios.
- Aplicando principios SOLID para mantener el código desacoplado y modular.
