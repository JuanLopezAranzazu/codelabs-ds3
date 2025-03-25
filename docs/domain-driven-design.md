# Codelab Domain-Driven Design (DDD)

## 1. ¿Qué es Domain-Driven Design (DDD) y cuál es su objetivo principal?
DDD es un enfoque de diseño de software centrado en el dominio del negocio, con el objetivo de crear modelos que reflejen fielmente la realidad del negocio y faciliten la comunicación entre desarrolladores y expertos en dominio.

## 2. ¿Cuál es la diferencia entre una Entidad y un Objeto de Valor en DDD?
- **Entidad**: Representa un objeto con identidad única dentro del dominio. Su identidad persiste a lo largo del tiempo, incluso si sus atributos cambian. Ejemplo: un **Usuario** con un identificador único (`id`).
- **Objeto de Valor**: Es un conjunto de atributos que describen un concepto del dominio sin identidad propia. Dos objetos de valor con los mismos datos son considerados iguales. Ejemplo: una **Dirección** compuesta por calle, ciudad y código postal; dos direcciones con los mismos datos son indistinguibles.

## 3. ¿Qué es un Bounded Context y por qué es importante en DDD?
Es un límite bien definido dentro del dominio donde se aplica un modelo específico. Es clave porque evita ambigüedades y permite modularidad en sistemas complejos.

## 4. ¿Cuál es el propósito del Lenguaje Ubicuo (Ubiquitous Language) en DDD?
Alinear el lenguaje entre desarrolladores y expertos del negocio para mejorar la comunicación y garantizar que el software refleje con precisión los conceptos del dominio.

## 5. ¿Qué es un Agregado (Aggregate) y cómo garantiza la consistencia en el dominio?
Es un grupo de entidades y objetos de valor que forman una unidad lógica, con una entidad raíz que controla el acceso y garantiza la consistencia de los datos.

## 6. ¿Cómo se relacionan los Repositorios en DDD con la infraestructura de persistencia?
Los repositorios actúan como intermediarios entre la lógica de dominio y la capa de persistencia, proporcionando acceso a los agregados sin exponer los detalles de la base de datos.

## 7. ¿Qué son los Eventos de Dominio y cómo mejoran la comunicación entre Bounded Contexts?
Son notificaciones sobre cambios en el estado del dominio, facilitando la integración y sincronización entre diferentes contextos sin acoplamiento fuerte.

## 8. ¿Cómo se diferencian los Servicios de Aplicación y los Servicios de Dominio en DDD?
- **Servicios de Dominio**: Contienen lógica de negocio que no encaja en una entidad o agregado.
- **Servicios de Aplicación**: Orquestan casos de uso, coordinando la interacción entre repositorios, entidades y servicios de dominio.

## 9. ¿Cómo DDD facilita el diseño de software en sistemas complejos y escalables?
Dividiendo el dominio en Bounded Contexts, promoviendo un modelo claro y estable, reduciendo acoplamiento y facilitando la escalabilidad y mantenibilidad.

## 10. ¿Cómo se puede combinar DDD con Clean Architecture en una aplicación de microservicios?
- Usando **Bounded Contexts** para definir microservicios con límites claros.
- Aplicando **Entidades y Agregados** dentro de la capa de dominio.
- Utilizando **Repositorios** en la capa de infraestructura para desacoplar la persistencia.
- Implementando **Eventos de Dominio** para la comunicación entre microservicios.
