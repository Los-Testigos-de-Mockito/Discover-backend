package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.Opinion;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.repositories.InmuebleRepository;
import com.example.discoverbackend.repositories.OpinionRepository;
import com.example.discoverbackend.repositories.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestOpinion {

    @Mock
    private OpinionRepository opinionRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InmuebleRepository inmuebleRepository;

    @InjectMocks
    private OpinionServiceImpl opinionService;
    @Test
    public void testOpinionCreation() {
        Usuario usuario = new Usuario();
        Inmueble inmueble = new Inmueble();
        Opinion opinion = new Opinion(usuario, inmueble, 4.5, "Great location and services");

        Assert.assertNotNull(opinion);
        Assert.assertEquals(Double.valueOf(4.5), opinion.getCalificacion());
        Assert.assertEquals("Great location and services", opinion.getObservaciones());
    }

}
