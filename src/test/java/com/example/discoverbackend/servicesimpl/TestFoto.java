package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.Foto;
import org.junit.Assert;
import org.junit.Test;

public class TestFoto {
    @Test
    public void testFotoCreation() {
        Foto foto = new Foto("http://example.com/photo.jpg");

        Assert.assertNotNull(foto);
        Assert.assertEquals("http://example.com/photo.jpg", foto.getPhotoLink());
    }
}
