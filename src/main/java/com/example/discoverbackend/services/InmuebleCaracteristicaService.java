package com.example.discoverbackend.services;


import com.example.discoverbackend.dtos.InmuebleCaracteristicaRequest;
import com.example.discoverbackend.entities.InmuebleCaracteristica;
import com.example.discoverbackend.entities.Opinion;
import com.example.discoverbackend.repositories.InmuebleCaracteristicaRepository;

import java.util.List;

public interface InmuebleCaracteristicaService {
    public InmuebleCaracteristica createInmuebleCaracteristica(InmuebleCaracteristicaRequest inmuebleCaracteristicaRequest);

//    public List<InmuebleCaracteristica> listCaracteristicaByInmueble (Long id);

}
