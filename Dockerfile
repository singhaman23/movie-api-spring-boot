# ===== Build stage =====
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# ===== Runtime stage =====
FROM eclipse-temurin:11-jre
WORKDIR /app

# copy jar with FIXED NAME and FIXED PATH
COPY --from=build /build/target/movie-api-1.0.0.jar /app/movie-api.jar

EXPOSE 8080

# ABSOLUTE PATH ENTRYPOINT
ENTRYPOINT ["java", "-jar", "/app/movie-api.jar"]
