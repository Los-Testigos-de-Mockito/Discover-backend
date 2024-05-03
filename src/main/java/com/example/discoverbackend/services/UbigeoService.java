package com.example.discoverbackend.services;

import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.Ubigeo;

public interface UbigeoService {
    public Ubigeo save(Ubigeo ubigeo);
    public Ubigeo listById(Long id);
}
