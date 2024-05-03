package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.dtos.RegisterUserRequest;
import com.example.discoverbackend.entities.RoleApplication;
import com.example.discoverbackend.entities.RoleUser;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.repositories.RoleRepository;
import com.example.discoverbackend.repositories.RoleUserRepository;
import com.example.discoverbackend.repositories.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@SpringBootTest
public class TestUsuario {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RoleUserRepository roleUserRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    public void testUsuarioCreation() {
        Usuario usuario = new Usuario("John", "Doe", "Smith", "12345678", "1234567890", "john.doe@example.com", "password123", "linkToPhotoDni", "linkToProfilePhoto", new Date(), new Date(), null);
        Assert.assertNotNull(usuario);
        Assert.assertEquals("John", usuario.getFirstName());
        Assert.assertEquals("Doe", usuario.getLastNameDad());
        Assert.assertEquals("Smith", usuario.getLastNameMom());
        Assert.assertEquals("12345678", usuario.getDni());
        Assert.assertEquals("john.doe@example.com", usuario.getEmail());
    }


    @Test
    public void testSaveUsuario() {
        RegisterUserRequest request = new RegisterUserRequest("John", "Doe", "Smith", "john.doe@example.com", "password", "1234567890", "12345678", "linkPhotoDni", new Date(), "linkPhotoProfile");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> i.getArguments()[0]);
        when(roleRepository.findByName(anyString())).thenReturn(new RoleApplication("USER"));
        when(roleUserRepository.save(any(RoleUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(encoder.encode(anyString())).thenReturn("encodedPassword");

        Usuario savedUser = usuarioService.save(request);

        Assert.assertNotNull(savedUser);
        Assert.assertEquals("John", savedUser.getFirstName());
        Assert.assertEquals("encodedPassword", savedUser.getPassword());
    }


}
