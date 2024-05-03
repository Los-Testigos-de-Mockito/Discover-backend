package com.example.discoverbackend.services;

import com.example.discoverbackend.dtos.PrincipalInmueblesResponse;
import com.example.discoverbackend.dtos.InmuebleRequest;
import com.example.discoverbackend.dtos.ShowInmuebleResponse;
import com.example.discoverbackend.entities.Inmueble;

import java.util.List;

public interface InmuebleService {

    public List<PrincipalInmueblesResponse> listAll();

    public ShowInmuebleResponse listDataInmueble(Long id);

    public Inmueble save(InmuebleRequest inmueble);

    public void delete(Long id, boolean forced);

    public List<String> getAllPropertiesTypes();

    public List<String> gettAllSharedRoom();
}
