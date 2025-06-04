package com.usermanagement.designation.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    //For Health CheckUp Test
    @GetMapping("api-health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Healthy");
    }

}