# BloodDonorJavaProject (Spring Boot)

## What is inside
- Java Spring Boot backend (REST API)
- JPA Entity for Donor
- Repository, Service, Controller layers
- application.properties configured for MySQL (change credentials)

## How to run
1. Install JDK 17+, Maven, and MySQL.
2. Create database: `CREATE DATABASE bloodbank;`
3. Update `src/main/resources/application.properties` with your DB user/password.
4. From project root: `mvn spring-boot:run`
5. APIs available at http://localhost:8080/api/donors
