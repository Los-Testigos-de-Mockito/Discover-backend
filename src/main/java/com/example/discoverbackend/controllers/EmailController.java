package com.example.discoverbackend.controllers;

import com.example.discoverbackend.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://main--discover-front.netlify.app")
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendSimpleMessage(to, subject, text);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }
}
