package com.example.discoverbackend.services;

import com.example.discoverbackend.dtos.AlquilerRequest;
import com.example.discoverbackend.dtos.AlquilerResponse;
import com.example.discoverbackend.entities.Alquiler;

import java.util.List;

public interface AlquilerService {
    public Alquiler createAlquiler(AlquilerRequest alquiler);

    public List<AlquilerResponse> listAlquilerByUser (Long id);

    public Alquiler updateAlquiler(Long id);

}
