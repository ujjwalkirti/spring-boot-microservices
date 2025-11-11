# Spring Boot Microservices — patient-service

This repository contains a small Spring Boot microservice example: the patient-service module which demonstrates a simple data model, JPA/H2 configuration and an embedded dataset initializer.

## Repository layout

- `patient-management/patient-service/` — the Spring Boot application (single service) with sources, resources, maven wrapper and tests.

Key source folders:

- `src/main/java/com/pm/patient_service` — application code (model, repository, service, mapper, controller)
- `src/main/resources` — config and initialization data (`application.properties`, `data.sql`)

## Tech stack

- Java: 21 (see `pom.xml` property `java.version`)
- Spring Boot: 3.5.7 (parent in `pom.xml`)
- Build: Maven (project includes `mvnw`/`mvnw.cmd` in the `patient-service` folder)
- Database (runtime): H2 in-memory (configured for dev/test). `data.sql` seeds the `patient` table with example rows.

## Prerequisites

- JDK 21 installed locally (or use a matching JDK on your PATH)
- Git and a terminal (PowerShell on Windows is fine)

Note: The project includes a Maven wrapper inside `patient-management/patient-service/` so you can build without installing Maven globally.

## Quick start (PowerShell)

1. Open a PowerShell terminal and change to the service folder:

	cd patient-management\patient-service

2. Run the application using the Maven wrapper:

	.\mvnw.cmd spring-boot:run

	or build and run the packaged jar:

	.\mvnw.cmd -DskipTests package
	java -jar target\patient-service-0.0.1-SNAPSHOT.jar

3. By default the service listens on port 4000 (see `server.port` in `application.properties`).

## Database and sample data

- The app uses an H2 in-memory database during development (configured in `application.properties`).
- `data.sql` creates a `patient` table (if it doesn't exist) and inserts a number of sample rows with fixed UUIDs. This makes it convenient to start and inspect example data immediately after startup.
- H2 console is reachable at `/h2-console` (application properties include `spring.application.path = /h2-console`) — confirm the exact mapping if you add a separate web configuration.

Important: `application.properties` contains an entry `spring.datasource.driver-clas-name=org.h2.Driver` which has a small typo (it should be `spring.datasource.driver-class-name`). The app may still work depending on Spring Boot defaults, but consider fixing the property name when changing datasource settings.

## REST API

- The code defines a `PatientController` with base path `/patients`, but as of this snapshot there are no REST handler methods implemented in that controller. Use the service and repository packages as starting points to implement CRUD endpoints (GET/POST/PUT/DELETE).

Suggested endpoints to implement next:

- GET /patients — list patients
- GET /patients/{id} — get a patient by id
- POST /patients — create a new patient
- PUT /patients/{id} — update an existing patient
- DELETE /patients/{id} — delete a patient

## Running tests

From `patient-management/patient-service` run:

.\mvnw.cmd test

This will execute the unit tests present in `src/test/java`.

## Development notes and next steps

- Add controller endpoints and DTO mapping (there are DTO/mapper classes present to follow the intended design).
- Add integration tests that start the application on a random port and verify the seeded data from `data.sql`.
- Provide a PostgreSQL profile for production-like testing (pom already contains the PostgreSQL runtime dependency). When switching, update `application.properties` or use profile-specific properties.
- Consider fixing the `driver-clas-name` typo in `application.properties`.

## Contributing

PRs are welcome. Please open issues for feature requests or bugs, and include a short description and steps to reproduce.

## License

See the repository `LICENSE` file at the project root.


