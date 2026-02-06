
# Movie API – Spring Boot REST Application

This project is a Java Spring Boot RESTful backend application for managing a collection of movies.
It was developed as part of a Freelance Java Developer sample task.

The application exposes REST APIs to add and retrieve items, uses in-memory storage, performs input validation, and is deployed on Railway.app.

---

## Live Deployment

Application URL:
https://movie-api-spring-boot-production.up.railway.app

Swagger UI:
https://movie-api-spring-boot-production.up.railway.app/swagger-ui.html

OpenAPI Docs:
https://movie-api-spring-boot-production.up.railway.app/v3/api-docs

Movies API:
https://movie-api-spring-boot-production.up.railway.app/api/movies

GitHub Repository:
https://github.com/singhaman23/movie-api-spring-boot

---

## Project Overview

This application demonstrates:
- RESTful API design using Spring Boot
- In-memory data storage
- Input validation with meaningful error messages
- Swagger/OpenAPI documentation
- Public deployment for demo and testing

---

## Task Requirements – Implementation

### 1. Item Model
A `Movie` entity is defined with the following fields:
- id
- title
- description
- releaseYear
- genre
- rating
- director

### 2. Data Storage
- In-memory data store implemented using `ConcurrentHashMap`
- Thread-safe operations
- Auto-incrementing IDs
- Sample movies loaded at startup

### 3. API Endpoints

| Method | Endpoint         | Description           |
|------- |------------------|-----------------------|
| POST   | /api/movies      | Add a new movie       |
| GET    | /api/movies/{id} | Get movie by ID       |
| GET    | /api/movies      | Get all movies        |
| PUT    | /api/movies/{id} | Update existing movie |
| DELETE | /api/movies/{id} | Delete movie          |

### 4. Input Validation
- Bean Validation annotations
- Required field checks
- Range validation for year and rating
- Clear error responses for invalid input

### 5. Documentation
- This README file
- Swagger UI for interactive API testing

### 6. Demo / Hosting
- Application deployed on Railway.app
- Public URLs shared for verification

---

## Sample API Usage

### Add a Movie

```bash
curl -X POST https://movie-api-spring-boot-production.up.railway.app/api/movies \
 -H "Content-Type: application/json" \
 -d '{
   "title": "Inception",
   "description": "A thief who steals corporate secrets through dream-sharing technology.",
   "releaseYear": 2010,
   "genre": "Sci-Fi",
   "rating": 8.8,
   "director": "Christopher Nolan"
 }'
````

### Get Movie by ID

```bash
curl https://movie-api-spring-boot-production.up.railway.app/api/movies/1
```

---

## Validation Rules

| Field        | Validation Rules        |
| ------------ | ----------------------- |
| Title        | Required, 1–200 chars   |
| Description  | Required, 10–1000 chars |
| Release Year | 1888–2025               |
| Genre        | Required                |
| Rating       | 0.0–10.0                |
| Director     | Required                |

---

## Swagger Documentation

Swagger UI is available at:
[https://movie-api-spring-boot-production.up.railway.app/swagger-ui.html](https://movie-api-spring-boot-production.up.railway.app/swagger-ui.html)

All endpoints can be tested directly from the browser.

---

## Run Locally

### Prerequisites

* Java 11 or higher
* Maven 3.6 or higher

### Steps

```bash
git clone https://github.com/singhaman23/movie-api-spring-boot.git
cd movie-api-spring-boot
mvn clean install
mvn spring-boot:run
```

Local URLs:

* API Base: [http://localhost:8080](http://localhost:8080)
* Movies API: [http://localhost:8080/api/movies](http://localhost:8080/api/movies)
* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Technology Stack

* Java 11
* Spring Boot
* Spring Web MVC
* Spring Validation
* SpringDoc OpenAPI
* Maven
* Docker
* Railway.app

---

## Application Structure

```
movie-api-spring-boot/
├── src/main/java/com/netflix/
│   ├── Main.java
│   └── api/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── model/
│       ├── dto/
│       └── exception/
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Deployment Status

Platform: Railway.app
Status: Active and running
Last Updated: February 2026

---

## Notes

* Application is hosted on a free tier, so initial requests may take a few seconds
* Use Swagger UI for easiest testing
* Health endpoint available at `/health`


