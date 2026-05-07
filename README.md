# SmartLogix Inventario MS

Microservicio de gestión de inventario para SmartLogix. Permite administrar el catálogo de productos y el stock disponible dentro de la arquitectura basada en microservicios del proyecto.

## Tecnologías
- Java 17
- Spring Boot
- Maven
- MySQL

## Requisitos
- Java 17 o superior
- Maven o Maven Wrapper
- MySQL en ejecución
- Base de datos configurada para el proyecto

## Configuración
Este microservicio se ejecuta en el puerto `8081`.

## Instalación y ejecución

```bash
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/productos | Listar productos |
| GET | /api/productos/{id} | Obtener producto por ID |
| POST | /api/productos | Crear producto |
| PUT | /api/productos/{id} | Actualizar producto |
| DELETE | /api/productos/{id} | Eliminar producto |

## Prueba rápida
1. Levantar la base de datos MySQL.
2. Ejecutar el microservicio.
3. Probar los endpoints con Postman, Swagger o a través del BFF.
4. Verificar respuesta en `http://localhost:8081`.

## Autor
Proyecto desarrollado para la asignatura **Desarrollo Fullstack III**.
