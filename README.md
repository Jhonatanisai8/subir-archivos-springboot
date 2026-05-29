# app-subir-archivos

Aplicación Spring Boot para subir archivos y almacenarlos usando JPA/MySQL.

## Tecnologías

- Java 21
- Spring Boot 4
- Spring Data JPA
- Spring Web MVC
- MySQL
- Lombok

## Requisitos

- JDK 21
- Maven
- Base de datos MySQL

## Configuración

La aplicación lee los datos de conexión desde variables en `application.yaml`:

```yaml
spring:
  datasource:
    url: ${URL}
    username: ${USER}
    password: ${PASS}
```

Puedes usar un archivo `.env` o configurar las variables de entorno:

- `URL`
- `USER`
- `PASS`

## Ejecución

Desde la raíz del proyecto:

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Build

Para compilar el proyecto:

```bash
./mvnw clean package
```

## Prueba

Ejecutar tests con:

```bash
./mvnw test
```

## Estructura principal

- `src/main/java/com/jhona/appsubirarchivos` — código de aplicación
- `src/main/resources/application.yaml` — configuración de Spring Boot
- `pom.xml` — dependencias y build

## Notas

Asegúrate de tener la base de datos MySQL disponible y accesible antes de iniciar la aplicación.
