package com.example.discoverbackend.controllers;

import com.example.discoverbackend.dtos.AlquilerResponse;
import com.example.discoverbackend.services.CaracteristicaService;
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
public class CaracteristicasController {
    @Autowired
    CaracteristicaService caracteristicaService;

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/caracteristica/{caracteristicas_id}")
    public ResponseEntity<List<String>> getCaracteristicasByTipoCaracteristicasId(@PathVariable Long caracteristicas_id) {
        return new ResponseEntity<>(caracteristicaService.getCaracteristicaByTipoCaracteristicaId(caracteristicas_id), HttpStatus.OK);
    }
}
