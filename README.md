# SmartLogix Inventario MS

Microservicio de gestión de inventario para SmartLogix. Permite registrar, consultar, actualizar y eliminar productos del inventario dentro de la arquitectura basada en microservicios del proyecto.

## Tecnologías
- Java 17
- Spring Boot 3.2.5
- Maven
- MySQL
- H2 Database (perfil dev)
- Springdoc OpenAPI / Swagger

## Requisitos
- Java 17 o superior
- Maven o Maven Wrapper
- MySQL en ejecución si se usa el perfil `mysql`

## Configuración
Este microservicio se ejecuta en el puerto `8081`.

Perfiles disponibles:
- `dev`: usa H2 en memoria para desarrollo y pruebas rápidas.
- `mysql`: usa MySQL local.

Base de datos utilizada en MySQL:
- Nombre: `smartlogix_inventario`

## Instalación y ejecución

### Ejecutar con perfil de desarrollo
```bash
java -jar target/inventario-ms-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

### Ejecutar con perfil MySQL
```bash
java -jar target/inventario-ms-0.0.1-SNAPSHOT.jar --spring.profiles.active=mysql
```

### Con Maven Wrapper en Windows PowerShell
```powershell
# Perfil dev (H2)
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"

# Perfil mysql
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

### Con Maven Wrapper en Bash / Linux / macOS
```bash
# Perfil dev (H2)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Perfil mysql
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Documentación API

- Swagger UI: `http://localhost:8081/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8081/api-docs`

## Consola H2

Disponible solo con perfil `dev`:

- URL: `http://localhost:8081/h2-console`
- JDBC URL: `jdbc:h2:mem:smartlogix_inventario`
- Usuario: `sa`
- Contraseña: *(vacía)*

## Endpoints
Documentados en Swagger UI según los controladores del microservicio.

## Estructura de perfiles

| Archivo | Perfil | Base de datos |
|--------|--------|--------------|
| `application.properties` | (base) | Puerto, Swagger, nombre de la app |
| `application-dev.properties` | `dev` | H2 en memoria |
| `application-mysql.properties` | `mysql` | MySQL local (`smartlogix_inventario`) |

## Prueba rápida
1. Ejecutar el microservicio con el perfil `dev`.
2. Abrir Swagger UI en `http://localhost:8081/swagger-ui.html`.
3. Probar los endpoints del inventario.
4. Si quieres usar MySQL local, cambiar al perfil `mysql`.

## Autor
Proyecto desarrollado para la asignatura **Desarrollo Fullstack III**.
