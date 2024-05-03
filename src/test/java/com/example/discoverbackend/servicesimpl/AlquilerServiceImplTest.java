package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.dtos.AlquilerRequest;
import com.example.discoverbackend.entities.Alquiler;
import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.Ubigeo;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.repositories.AlquilerRepository;
import com.example.discoverbackend.repositories.InmuebleRepository;
import com.example.discoverbackend.repositories.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AlquilerServiceImplTest {

    @Mock
    private AlquilerRepository alquilerRepository;
    @InjectMocks
    private AlquilerServiceImpl alquilerService;


    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InmuebleRepository inmuebleRepository;

    @Test
    public void testCreateAlquilerInmuebleAlreadyRented() {
        Usuario client = new Usuario();
        client.setId(1L);

        Inmueble inmueble = new Inmueble();
        inmueble.setId(1L);

        Alquiler existingAlquiler = new Alquiler(client, inmueble, 500.0, new Date(), true);

        lenient().when(alquilerRepository.findByClient_Id(any())).thenReturn(Arrays.asList(existingAlquiler));
        lenient().when(inmuebleRepository.findById(any())).thenReturn(Optional.of(inmueble));
        lenient().when(usuarioRepository.findById(any())).thenReturn(Optional.of(client));

        AlquilerRequest request = new AlquilerRequest();
        request.setClient_id(1L);
        request.setInmueble_id(1L);
        request.setPrice(500.0);
        request.setTransactionDate(new Date());

        Exception exception = Assert.assertThrows(RuntimeException.class, () -> {
            alquilerService.createAlquiler(request);
        });

        Assert.assertEquals("Inmueble ya alquilado", exception.getMessage());
    }



    @Test
    public void testAlquilerCreation() {
        Usuario client = new Usuario();
        Inmueble inmueble = new Inmueble();
        Date transactionDate = new Date();
        Alquiler alquiler = new Alquiler(client, inmueble, 500.0, transactionDate, true);

        Assert.assertNotNull(alquiler);
        Assert.assertTrue(alquiler.getActivate());
        Assert.assertEquals(Double.valueOf(500.0), alquiler.getPrice());
    }

    @Test
    public void testUpdateAlquiler(){
        Long id = 1L;

        Alquiler alquiler = new Alquiler();
        alquiler.setActivate(false);

        Usuario cliente = new Usuario();
        alquiler.setClient(cliente);

        Ubigeo ubigeo = new Ubigeo();

        Inmueble inmueble = new Inmueble();
        inmueble.setUbigeo(ubigeo);

        alquiler.setInmueble(inmueble);

        when(alquilerRepository.findById(id)).thenReturn(Optional.of(alquiler));

        alquiler.setInmueble(inmueble);

        when(alquilerRepository.findById(id)).thenReturn(Optional.of(alquiler));
        when(alquilerRepository.findById(id)).thenReturn(Optional.of(alquiler));
        when(alquilerRepository.findById(id)).thenReturn(Optional.of(alquiler));
        when(alquilerRepository.save(any(Alquiler.class))).thenAnswer(i -> i.getArguments()[0]);
        Alquiler newAlquiler = alquilerService.updateAlquiler(id);

        Assertions.assertTrue(newAlquiler.getActivate());
    }
}