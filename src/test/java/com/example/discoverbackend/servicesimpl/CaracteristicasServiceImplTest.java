package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.Caracteristica;
import com.example.discoverbackend.entities.TipoCaracteristica;
import com.example.discoverbackend.repositories.CaracteristicaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CaracteristicasServiceImplTest {

    @Mock
    private CaracteristicaRepository caracteristicaRepository;

    @Test
    public void testCaracteristicaCreation() {
        TipoCaracteristica tipoCaracteristica = new TipoCaracteristica("Type1");
        Caracteristica caracteristica = new Caracteristica("Air Conditioning", tipoCaracteristica, "icon.png");

        Assert.assertNotNull(caracteristica);
        Assert.assertEquals("Air Conditioning", caracteristica.getName());
        Assert.assertEquals("icon.png", caracteristica.getIcon());
    }

    @Test
    public void testTipoCaracteristicaCreation() {
        TipoCaracteristica tipoCaracteristica = new TipoCaracteristica("Amenities");

        Assert.assertNotNull(tipoCaracteristica);
        Assert.assertEquals("Amenities", tipoCaracteristica.getName());
    }

    @Test
    public void testGetCaracteristicaByTipoCaracteristicaId(){
        Long id = 1L;

        List<String> Result = new ArrayList<>();
        Result.add("Test");
        Mockito.when(caracteristicaRepository.getCaracteristicaByTipoCaracteristicaId(id)).thenReturn(Result);

        List<String> result = caracteristicaRepository.getCaracteristicaByTipoCaracteristicaId(id);

        Assertions.assertTrue(!result.isEmpty());
    }
}