# 🎬 Movie API – Netflix-like Backend Application

## Live Demo

* **Application URL:** [https://movie-api-spring-boot.onrender.com](https://movie-api-spring-boot.onrender.com)
* **Swagger UI:** [https://movie-api-spring-boot.onrender.com/swagger-ui.html](https://movie-api-spring-boot.onrender.com/swagger-ui.html)
* **Health Check:** [https://movie-api-spring-boot.onrender.com/health](https://movie-api-spring-boot.onrender.com/health)
* **Movies API:** [https://movie-api-spring-boot.onrender.com/api/movies](https://movie-api-spring-boot.onrender.com/api/movies)
* **GitHub Repository:** [https://github.com/YOUR_USERNAME/movie-api-spring-boot](https://github.com/YOUR_USERNAME/movie-api-spring-boot)

---

## Project Overview

A production-ready Java Spring Boot REST API for managing a collection of movies (Netflix-like backend). The application uses in-memory storage, comprehensive validation, global exception handling, and Swagger/OpenAPI documentation.

---

## Task Requirements – Completed

| Requirement      | Status     | Details                                                                 |
| ---------------- | ---------- | ----------------------------------------------------------------------- |
| Item Model       | ✅ Complete | Movie with id, title, description, releaseYear, genre, rating, director |
| Data Storage     | ✅ Complete | In-memory using ConcurrentHashMap (thread-safe)                         |
| API Endpoints    | ✅ Complete | POST /api/movies, GET /api/movies/{id} (+ bonus CRUD)                   |
| Input Validation | ✅ Complete | Bean Validation with clear error messages                               |
| Documentation    | ✅ Complete | README + Swagger UI                                                     |
| Deployment       | ✅ Complete | Live on Render.com                                                      |

---

## Quick Start – Run Locally

### Prerequisites

* Java 11+
* Maven 3.6+

### Steps

```bash
# Clone repository
git clone https://github.com/YOUR_USERNAME/movie-api-spring-boot.git
cd movie-api-spring-boot

# Build
mvn clean install

# Run
mvn spring-boot:run
```

### Local URLs

* API Base: [http://localhost:8080](http://localhost:8080)
* Movies API: [http://localhost:8080/api/movies](http://localhost:8080/api/movies)
* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## API Endpoints

| Method | Endpoint         | Description     |
| ------ | ---------------- | --------------- |
| POST   | /api/movies      | Add a new movie |
| GET    | /api/movies/{id} | Get movie by ID |
| GET    | /api/movies      | Get all movies  |
| PUT    | /api/movies/{id} | Update movie    |
| DELETE | /api/movies/{id} | Delete movie    |

---

## Sample API Usage

### Add Movie

```bash
curl -X POST https://movie-api-spring-boot.onrender.com/api/movies \
 -H "Content-Type: application/json" \
 -d '{
   "title": "Inception",
   "description": "A thief who steals corporate secrets through dream-sharing technology.",
   "releaseYear": 2010,
   "genre": "Sci-Fi",
   "rating": 8.8,
   "director": "Christopher Nolan"
 }'
```

---

## Validation Rules

| Field        | Rules                   |
| ------------ | ----------------------- |
| Title        | Required, 1–200 chars   |
| Description  | Required, 10–1000 chars |
| Release Year | 1888–2025               |
| Genre        | Required                |
| Rating       | 0.0–10.0                |
| Director     | Required                |

---

## Data Storage

* In-memory storage using `ConcurrentHashMap`
* Thread-safe operations
* Auto-increment ID generation
* Sample data preloaded on startup

---

## Swagger Documentation

Interactive API documentation:

```
https://movie-api-spring-boot.onrender.com/swagger-ui.html
```

---

## Deployment

### Live Application (Render.com – Free Tier)

* **App:** [https://movie-api-spring-boot.onrender.com](https://movie-api-spring-boot.onrender.com)
* **Swagger:** [https://movie-api-spring-boot.onrender.com/swagger-ui.html](https://movie-api-spring-boot.onrender.com/swagger-ui.html)
* **Health:** [https://movie-api-spring-boot.onrender.com/health](https://movie-api-spring-boot.onrender.com/health)

### Deploy on Render

1. Sign up at [https://render.com](https://render.com)
2. New → Web Service → Connect GitHub repo
3. Configure:

```
Environment: Docker
Build Command: mvn clean install
Start Command: java -jar target/movie-api-1.0.0.jar
Plan: Free
```

4. Add env variable:

```
PORT=8080
```

5. Deploy 🚀

---

## Build for Production

```bash
mvn clean package
java -jar target/movie-api-1.0.0.jar
```

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
├── pom.xml
└── README.md
```

---

## Production-Ready Features

* RESTful API design
* Global exception handling
* Input validation
* Swagger UI
* CORS enabled
* Health monitoring endpoint
* Thread-safe storage

---

## Deployment Status

✅ **Application Successfully Deployed**

**Platform:** Render.com (Free Tier)

**Status:** Active & Running

**Last Updated:** February 2026

---

## Support

1. Check `/health`
2. Verify Swagger UI
3. Review Render logs


```
