# Ecommerce Microservices with Docker

Este repositorio contiene un proyecto de Ecommerce implementado con microservicios en Java. Se utiliza Docker para facilitar la construcción, ejecución y gestión de los servicios.

## Microservicios

El proyecto incluye los siguientes microservicios:

* **api-gateway:**  API Gateway para enrutar las solicitudes a los microservicios correspondientes.
* **discovery-server:**  Servidor de descubrimiento para que los microservicios se registren y se encuentren entre sí.
* **order-management-service:**  Microservicio para la gestión de pedidos.
* **product-catalog-service:**  Microservicio para el catálogo de productos.

## Docker Compose

Se proporciona un archivo `docker-compose.yml` para gestionar los servicios y sus dependencias.

### Servicios incluidos:

* **Bases de datos:**
    * `product-catalog-db`: Base de datos PostgreSQL para el microservicio `product-catalog-service`.
    * `order-management-db`: Base de datos PostgreSQL para el microservicio `order-management-service`.
    * `keycloak-db`: Base de datos PostgreSQL para Keycloak.
* **Keycloak:**
    * `keycloak`: Servidor de autorización Keycloak para la gestión de identidades y accesos.
* **Construcción con Maven:**
    * `maven-build-discovery-server`:  Construye el microservicio `discovery-server`.
    * `maven-build-product`: Construye el microservicio `product-catalog-service`.
    * `maven-build-order`: Construye el microservicio `order-management-service`.
    * `maven-build-api-gateway`: Construye el microservicio `api-gateway`.
* **Microservicios:**
    * `discovery-server`: Ejecuta el microservicio `discovery-server`.
    * `api-gateway-server`: Ejecuta el microservicio `api-gateway`.
