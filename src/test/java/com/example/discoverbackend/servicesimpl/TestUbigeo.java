package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.Ubigeo;
import com.example.discoverbackend.repositories.UbigeoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
public class TestUbigeo {

    @Mock
    private UbigeoRepository ubigeoRepository;

    @InjectMocks
    private UbigeoServiceImpl ubigeoService;

    @Test
    public void testSaveUbigeo() {
        Ubigeo ubigeo = new Ubigeo(100, "Miraflores", "Lima", "Lima", null);
        when(ubigeoRepository.save(any(Ubigeo.class))).thenReturn(ubigeo);

        Ubigeo savedUbigeo = ubigeoService.save(ubigeo);

        Assert.assertNotNull(savedUbigeo);
        Assert.assertEquals("Lima", savedUbigeo.getDepartamento());
    }

    @Test
    public void testUbigeoCreation() {
        Ubigeo ubigeo = new Ubigeo(100, "Miraflores", "Lima", "Lima", null);

        Assert.assertNotNull(ubigeo);
        Assert.assertEquals("Lima", ubigeo.getDepartamento());
        Assert.assertEquals("Miraflores", ubigeo.getDistrito());
    }


}
