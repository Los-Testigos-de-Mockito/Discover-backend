package com.example.discoverbackend.controllers;

import com.example.discoverbackend.dtos.OpinionRequest;
import com.example.discoverbackend.entities.Alquiler;
import com.example.discoverbackend.entities.Opinion;
import com.example.discoverbackend.services.AlquilerService;
import com.example.discoverbackend.services.OpinionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://discover-mock.netlify.app")
@RestController
@RequestMapping("/api")
public class OpinionController {

    @Autowired
    OpinionService opinionService;

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/opinion")
    public ResponseEntity<Opinion> saveOpinion(@RequestBody OpinionRequest opinion) {
        Opinion saveOpinion = opinionService.createOpinion(opinion);
        return new ResponseEntity<Opinion>(saveOpinion, HttpStatus.CREATED);
    }
}
