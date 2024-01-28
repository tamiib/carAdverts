Car Adverts REST API
Overview
This RESTful web service allows for the creation, viewing, modification, and deletion of car advertisements. It is built using Java 17 and Spring Boot 3, with a PostgreSQL database running in Docker.

Tech Stack
Java 17
Spring Boot 3 Framework (Gradle - Kotlin)
PostgreSQL (Dockerized)
Project Structure
src/main/java/com/codevibe/codevibe/
controllers: Contains controller classes.
converters: Includes converters for objects, used to convert DTOs to domain models and vice versa.
dto: Contains Data Transfer Objects (DTOs) used on the frontend to avoid directly displaying domain models from the database.
domainModels: Contains entities from the database, used for data persistence.
repositories: Repositories for direct database communication.
security: Contains files for API access configuration.
serializers: Manages object serialization in a customized manner.
services: Service classes for communication between controllers and repositories, handling layer-specific objects.
validators: Validators for car adverts.
build.gradle.kts: Defines the build configuration.
docker-compose.yml: Configures and defines the PostgreSQL database.
