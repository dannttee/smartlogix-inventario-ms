# SmartLogix Inventario MS

Microservicio de gestión de inventario para SmartLogix. Administra el catálogo de productos y stock disponible.

## Tecnologías
- Java 17
- Spring Boot
- Maven
- MySQL/MariaDB

## Requisitos
- Java 17+
- Maven
- Base de datos MySQL corriendo

## Instalación y ejecución

```bash
.\mvnw spring-boot:run
```

## Endpoints disponibles
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/productos | Listar productos |
| GET | /api/productos/{id} | Obtener producto |
| POST | /api/productos | Crear producto |
| PUT | /api/productos/{id} | Actualizar producto |
| DELETE | /api/productos/{id} | Eliminar producto |
