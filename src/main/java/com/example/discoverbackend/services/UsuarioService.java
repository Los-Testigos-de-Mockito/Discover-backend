package com.example.discoverbackend.services;

import com.example.discoverbackend.dtos.DTOContactoUsuario;
import com.example.discoverbackend.dtos.LogInResponse;
import com.example.discoverbackend.dtos.LoginRequest;
import com.example.discoverbackend.dtos.RegisterUserRequest;
import com.example.discoverbackend.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService {

    public Usuario save(RegisterUserRequest usuario);

    public DTOContactoUsuario listContactoUsuario(Long id);

    public LogInResponse login(LoginRequest loginRequest);
}
