package org.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        printStartupInfo();
    }

    private static void printStartupInfo() {
        System.out.println("========================================");
        System.out.println("Movie API Started Successfully!");
        System.out.println("========================================");
        System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("API Docs: http://localhost:8080/v3/api-docs");
        System.out.println("API: http://localhost:8080/api/movies");
        System.out.println("========================================");
    }
}