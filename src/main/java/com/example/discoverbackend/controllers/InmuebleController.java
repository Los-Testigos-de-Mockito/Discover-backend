package com.example.discoverbackend.controllers;

import com.example.discoverbackend.dtos.InmuebleRequest;
import com.example.discoverbackend.dtos.PrincipalInmueblesResponse;
import com.example.discoverbackend.dtos.ShowInmuebleResponse;
import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.services.InmuebleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/api")
public class InmuebleController {

    @Autowired
    InmuebleService inmuebleService;

    //get de dto de inmueblefoto
    //@PreAuthorize("hasRole('USER')")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/inmuebles")
    public ResponseEntity<List<PrincipalInmueblesResponse>> getAllInmuebles() {
        List<PrincipalInmueblesResponse> inmuebles = inmuebleService.listAll();
        return new ResponseEntity<List<PrincipalInmueblesResponse>>(inmuebles, HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/inmuebles/{id}")
    public ResponseEntity<ShowInmuebleResponse> getInmueblesById(@PathVariable("id") Long id) {
        ShowInmuebleResponse inmueble = inmuebleService.listDataInmueble(id);
        return new ResponseEntity<ShowInmuebleResponse>(inmueble, HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/inmuebles")
    public ResponseEntity<Inmueble> createInmueble(@RequestBody InmuebleRequest inmueble) {
        Inmueble savedInmueble = inmuebleService.save(inmueble);
        return new ResponseEntity<Inmueble>(savedInmueble, HttpStatus.CREATED);

    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @DeleteMapping("/inmuebles/{id}")
    public ResponseEntity<HttpStatus> deleteInmueble(@PathVariable("id") Long id) {
        inmuebleService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/properties-types")
    public ResponseEntity<List<String>> getAllPropertiesTypes() {
        return new ResponseEntity<List<String>>(inmuebleService.getAllPropertiesTypes(), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/shared-room")
    public ResponseEntity<List<String>> getAllSharedRooms() {
        return new ResponseEntity<List<String>>(inmuebleService.gettAllSharedRoom(), HttpStatus.OK);
    }

}
