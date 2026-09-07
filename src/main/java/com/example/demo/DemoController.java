package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    // GET / -> short welcome naming the stack
    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "message", "Welcome! This is a Java + Maven (Spring Boot) DevOps practice app.",
                "stack", "Java 17 / Spring Boot / Maven",
                "endpoints", "/  /health  /api/items"
        );
    }

    // GET /health -> 200 with {"status":"ok"}
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    // GET /api/items -> array of 4 sample items
    @GetMapping("/api/items")
    public List<Map<String, Object>> items() {
        return List.of(
                Map.of("id", 1, "name", "Dockerize me"),
                Map.of("id", 2, "name", "Add a CI/CD pipeline"),
                Map.of("id", 3, "name", "Deploy to Kubernetes"),
                Map.of("id", 4, "name", "Go live on AWS")
        );
    }
}
