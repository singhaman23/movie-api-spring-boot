# Movie API - Netflix-like Backend Application

## Project Overview
This is a complete Java Spring Boot REST API for managing a collection of movies (similar to Netflix). It implements a RESTful API with in-memory storage, comprehensive validation, and Swagger documentation.

## Task Requirements Completed

| Requirement | Status | Implementation Details |
|------------|--------|------------------------|
| Item Model | Complete | Movie class with properties: id, title, description, releaseYear, genre, rating, director |
| Data Storage | Complete | In-memory storage using ConcurrentHashMap (thread-safe alternative to ArrayList) |
| API Endpoints | Complete | POST /api/movies (add item), GET /api/movies/{id} (get single item) - Plus bonus endpoints: GET /api/movies, PUT /api/movies/{id}, DELETE /api/movies/{id} |
| Input Validation | Complete | Bean Validation with custom error messages for all required fields |
| Documentation | Complete | This README + Swagger UI + Comprehensive code comments |
| Demo | Ready | Application can be deployed on free cloud platforms (instructions below) |

## Quick Start - Run Locally

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Git (optional for cloning)

### Step-by-Step Local Setup

1. Clone or download the project
2. Navigate to project directory
   ```bash
   cd Netflix-Application
   ```
3. Build the application
   ```bash
   mvn clean install
   ```
4. Run the application
   ```bash
   mvn spring-boot:run
   ```
   Or run the Main.java file directly from your IDE.

5. Access the application
    - API Base URL: http://localhost:8080
    - Movies API: http://localhost:8080/api/movies
    - Swagger UI: http://localhost:8080/swagger-ui.html
    - OpenAPI Docs: http://localhost:8080/v3/api-docs

## Project Structure
```
src/main/java/com/netflix/
├── Main.java                          # Application entry point
└── api/
    ├── config/                        # Configuration classes
    ├── controller/                    # REST API endpoints
    ├── dto/                           # Data Transfer Objects
    ├── exception/                     # Exception handling
    ├── model/                         # Entity models
    ├── repository/                    # Data access layer
    └── service/                       # Business logic
```

## API Endpoints

### Base URL: http://localhost:8080/api/movies

| Method | Endpoint | Description | Required in Task |
|--------|----------|-------------|------------------|
| POST | / | Add a new movie | REQUIRED |
| GET | /{id} | Get movie by ID | REQUIRED |
| GET | / | Get all movies | Bonus |
| PUT | /{id} | Update a movie | Bonus |
| DELETE | /{id} | Delete a movie | Bonus |

## API Usage Examples

### 1. Add a New Movie (POST) - REQUIRED ENDPOINT
```bash
curl -X POST "http://localhost:8080/api/movies" \
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

Response (201 Created):
```json
{
  "success": true,
  "message": "Movie added successfully",
  "data": {
    "id": 1,
    "title": "Inception",
    "description": "A thief who steals corporate secrets through dream-sharing technology.",
    "releaseYear": 2010,
    "genre": "Sci-Fi",
    "rating": 8.8,
    "director": "Christopher Nolan",
    "createdAt": "2024-01-15",
    "updatedAt": "2024-01-15"
  }
}
```

### 2. Get Movie by ID (GET) - REQUIRED ENDPOINT
```bash
curl -X GET "http://localhost:8080/api/movies/1"
```

Response (200 OK):
```json
{
  "success": true,
  "message": "Movie found",
  "data": {
    "id": 1,
    "title": "Inception",
    "description": "A thief who steals corporate secrets through dream-sharing technology.",
    "releaseYear": 2010,
    "genre": "Sci-Fi",
    "rating": 8.8,
    "director": "Christopher Nolan",
    "createdAt": "2024-01-15",
    "updatedAt": "2024-01-15"
  }
}
```

## Validation Rules

All movie requests are validated with these rules:

| Field | Validation Rules | Error Message |
|-------|-----------------|---------------|
| Title | Required, 1-200 characters | "Title is required" / "Title must be between 1 and 200 characters" |
| Description | Required, 10-1000 characters | "Description is required" / "Description must be between 10 and 1000 characters" |
| Release Year | Required, between 1888-2025 | "Release year is required" / "Release year must be after 1888" / "Release year cannot be in the future" |
| Genre | Required | "Genre is required" |
| Rating | Between 0.0 and 10.0 | "Rating must be at least 0.0" / "Rating cannot exceed 10.0" |
| Director | Required | "Director is required" |

## Testing Validation - Invalid Request Example

```bash
curl -X POST "http://localhost:8080/api/movies" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "",
    "description": "Short",
    "releaseYear": 1700,
    "genre": "Unknown",
    "rating": 15.0,
    "director": ""
  }'
```

Expected Response (400 Bad Request):
```json
{
  "success": false,
  "message": "Title is required; Description must be between 10 and 1000 characters; Release year must be after 1888; Invalid genre. Must be one of: [Action, Adventure, Comedy, Drama, Sci-Fi, Thriller, Horror, Romance, Documentary, Animation]; Rating cannot exceed 10.0; Director is required;",
  "data": null
}
```

## Data Storage Implementation

The application uses in-memory data storage as required:

1. Storage Type: In-memory using ConcurrentHashMap
2. Thread Safety: ConcurrentHashMap ensures thread-safe operations
3. Data Persistence: Data persists while application is running
4. ID Generation: Auto-incrementing IDs
5. Sample Data (pre-loaded on startup):
    - Inception (2010) - Sci-Fi, Rating: 8.8, Director: Christopher Nolan
    - The Shawshank Redemption (1994) - Drama, Rating: 9.3, Director: Frank Darabont

## Code Structure Details

### 1. Movie Model (Item Model)
File: `src/main/java/com/netflix/api/model/Movie.java`
```java
public class Movie {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String genre;
    private Double rating;
    private String director;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    // Constructors, getters, setters
}
```

### 2. Data Storage (In-memory)
File: `src/main/java/com/netflix/api/repository/MovieRepository.java`
```java
@Repository
public class MovieRepository {
    private final Map<Long, Movie> movieStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    // CRUD operations: addMovie(), getMovieById(), etc.
}
```

### 3. API Endpoints
File: `src/main/java/com/netflix/api/controller/MovieController.java`
```java
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    @PostMapping
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequest movieRequest) {
        // Add movie with validation
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        // Get movie by ID
    }
    
    // Bonus endpoints
    @GetMapping
    public ResponseEntity<?> getAllMovies() { ... }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(...) { ... }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(...) { ... }
}
```

### 4. Input Validation
File: `src/main/java/com/netflix/api/dto/MovieRequest.java`
```java
public class MovieRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;
    
    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be after 1888")
    @Max(value = 2025, message = "Release year cannot be in the future")
    private Integer releaseYear;
    
    // More validation annotations...
}
```

## Swagger UI Documentation

Interactive API documentation is available at:
```
http://localhost:8080/swagger-ui.html
```

Features:
- Try all endpoints directly from browser
- See request/response schemas
- View validation rules
- Test with real data
- No additional setup required

## Deployment Instructions

### Option 1: Railway.app (Recommended - Free & Easiest)

Step-by-Step Deployment:

1. Create account at railway.app (free)
2. Click "New Project" -> "Deploy from GitHub repo"
3. Authorize GitHub access
4. Select your repository
5. Railway automatically detects Spring Boot app
6. Wait 2-3 minutes for deployment
7. Click on the generated URL to test
8. Share the URL: https://your-app-name.up.railway.app

### Option 2: Render.com (Free Tier)

Step-by-Step Deployment:

1. Sign up at render.com
2. Click "New +" -> "Web Service"
3. Connect your GitHub repository
4. Configure:
    - Name: movie-api
    - Environment: Docker
    - Region: Choose closest
    - Build Command: mvn clean install
    - Start Command: java -jar target/movie-api-1.0.0.jar
5. Click "Create Web Service"
6. Wait 3-5 minutes
7. Test: https://your-app-name.onrender.com

## Building for Production

Create executable JAR:
```bash
mvn clean package
```

The JAR file will be created at:
```
target/movie-api-1.0.0.jar
```

Run JAR file:
```bash
java -jar target/movie-api-1.0.0.jar
```

## Testing the Deployed Application

After deployment, test these endpoints:

1. Health Check: https://your-app-url/api/movies
2. Swagger UI: https://your-app-url/swagger-ui.html
3. API Documentation: https://your-app-url/v3/api-docs

## Technical Details

### Dependencies:
- Spring Boot 2.7.0 - Main framework
- Spring Web MVC - REST API
- Spring Validation - Input validation
- SpringDoc OpenAPI 1.6.9 - API documentation
- Java 11 - Runtime

### Key Features:
- RESTful API design with proper HTTP methods
- Comprehensive input validation with meaningful error messages
- Global exception handling
- Consistent API response format
- CORS enabled for frontend integration
- Thread-safe data storage
- Automatic API documentation with Swagger
- Pre-loaded sample data
- Clean package structure





**Demo URL:** [Your deployed URL will appear here after deployment]

**GitHub Repository:** [Your repository URL]

**Swagger UI:** [Your URL]/swagger-ui.html