package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.Ubigeo;
import com.example.discoverbackend.repositories.UbigeoRepository;
import com.example.discoverbackend.services.UbigeoService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbigeoServiceImpl implements UbigeoService {

    @Autowired
    UbigeoRepository ubigeoRepository;

    public Ubigeo listById(Long id){
        Ubigeo ubigeo;
        ubigeo=ubigeoRepository.findById(id).get();
        return ubigeo;
    }

    @Transactional
    public Ubigeo save(Ubigeo ubigeo){
        Ubigeo newUbigeo = ubigeoRepository.save(new Ubigeo(ubigeo.getIdUbigeo(), ubigeo.getDistrito(), ubigeo.getProvincia(), ubigeo.getDepartamento(), ubigeo.getInmuebleZonaList()));
        return newUbigeo;
    }
}
