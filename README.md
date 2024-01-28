Car Adverts REST API

Overview:
This RESTful web service allows for the creation, viewing, modification, and deletion of car advertisements. It is built using Java 17 and Spring Boot 3, with a PostgreSQL database running in Docker.

Tech Stack;
Java 17
Spring Boot 3 Framework (Gradle - Kotlin)
PostgreSQL (Dockerized)

Project Structure:
src/main/java/com/codevibe/codevibe/
- controllers: Contains controller classes.
- converters: Includes converters for objects, used to convert DTOs to domain models and vice versa.
- dto: Contains Data Transfer Objects (DTOs) used on the frontend to avoid directly displaying domain models from the database.
- domainModels: Contains entities from the database, used for data persistence.
- repositories: Repositories for direct database communication.
- security: Contains files for API access configuration.
- serializers: Manages object serialization in a customized manner.
- services: Service classes for communication between controllers and repositories, handling layer-specific objects.
- validators: Validators for car adverts.
- build.gradle.kts: Defines the build configuration.
- docker-compose.yml: Configures and defines the PostgreSQL database.

Running the Project Locally:
1. Clone the Repository:
   git clone https://github.com/tamiib/carAdverts.git

2. Open the Project in Visual Studio Code:
-Launch Visual Studio Code.
-Open the cloned repository by selecting File > Open Folder... and navigating to the cloned directory.
**Install Recommended Extensions for VS Code:
   - Java Extension Pack: Essential Java development tools, including support for Java, Maven, and debugging.
   - Spring Boot Extension Pack: Useful for Spring Boot application development. Includes support for running and debugging Spring applications.
   - Docker: Simplifies the management of Docker containers, images, and Docker Compose files.
   - Gradle for Java: Helps in managing Gradle projects and executing Gradle tasks.

3. Start Docker Daemon:
-Ensure Docker is running on your machine. Start Docker Desktop or use the appropriate command to start the Docker service in your operating system.

4. Start the PostgreSQL Database:
   docker-compose up

5. Run the Application:
   -Using VS Code: - navigate to the 'Run and Debug' view in VS Code.
                   -select 'Spring Boot Application' from the dropdown.
                   -click the 'Run' button.
   -Using Terminal: - run the application by executing command in the terminal: ./gradlew bootRun

6. Open brawser and go to http://localhost:8080/swagger-ui/index.html#/
   -username:codevibe
   -password:tami
