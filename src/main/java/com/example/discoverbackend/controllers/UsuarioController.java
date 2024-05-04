package com.example.discoverbackend.controllers;

import com.example.discoverbackend.dtos.DTOContactoUsuario;
import com.example.discoverbackend.dtos.LogInResponse;
import com.example.discoverbackend.dtos.LoginRequest;
import com.example.discoverbackend.dtos.RegisterUserRequest;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.services.UsuarioService;
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
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LogInResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(usuarioService.login(loginRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<Usuario> createUsuario(@RequestBody RegisterUserRequest usuario){
        Usuario savedUsuario = usuarioService.save(usuario);
        return new ResponseEntity<Usuario>(savedUsuario, HttpStatus.CREATED);
    }
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/contact/{id}")
    public ResponseEntity<DTOContactoUsuario> listContactUsuario(@PathVariable("id") Long id){
        DTOContactoUsuario dtoContactoUsuarios = usuarioService.listContactoUsuario(id);
        return new ResponseEntity<DTOContactoUsuario>(dtoContactoUsuarios, HttpStatus.OK);
    }
}
