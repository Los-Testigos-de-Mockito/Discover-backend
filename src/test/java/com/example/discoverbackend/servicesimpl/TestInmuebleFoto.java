package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.Foto;
import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.InmuebleFoto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestInmuebleFoto {
    @Test
    public void testInmuebleFotoCreation() {
        Inmueble inmueble = new Inmueble();
        Foto foto = new Foto("http://example.com/photo.jpg");
        InmuebleFoto inmuebleFoto = new InmuebleFoto(inmueble, foto);

        Assert.assertNotNull(inmuebleFoto);
        Assert.assertEquals(foto, inmuebleFoto.getFoto());
    }

}
