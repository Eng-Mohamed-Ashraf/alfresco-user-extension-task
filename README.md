![Java](https://img.shields.io/badge/Java-17-red)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3-green)
![Maven](https://img.shields.io/badge/Maven-Build-blue)

# Alfresco User Extension – Java 17 Maven Task

This project is a **Java 17 + Maven + Spring Boot** implementation that simulates
extending Alfresco `cm:person` with two additional properties:

- `authenticationMethod` (string, allowed: `Password`, `OTP`, `MFA`, default = `Password`)
- `managerName` (string, optional, must reference an existing user)

The focus is on:

- Clean Java OOP
- REST APIs (GET /users/{username}, PUT /users/{username})
- Business validation for the new fields
- In-memory storage to simulate Alfresco users

## Tech Stack

- Java 17
- Maven
- Spring Boot 3 (Web, Validation)

## How to Run

```bash
mvn clean package
mvn spring-boot:run
