package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.dtos.OpinionRequest;
import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.Opinion;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.repositories.InmuebleRepository;
import com.example.discoverbackend.repositories.OpinionRepository;
import com.example.discoverbackend.repositories.UsuarioRepository;
import com.example.discoverbackend.services.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    OpinionRepository opinionRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    InmuebleRepository inmuebleRepository;

    public Opinion createOpinion(OpinionRequest opinion) {
        Usuario usuario = usuarioRepository.findById(opinion.getUser_id()).get();
        Inmueble inmueble = inmuebleRepository.findById(opinion.getProperty_id()).get();
        Opinion newOpinion = new Opinion(usuario, inmueble, opinion.getQualification(), opinion.getObservation());
        Opinion savedOpinion = opinionRepository.save(newOpinion);
        savedOpinion.getUsuario().setOpiniones(null);
        savedOpinion.getUsuario().setInmuebles(null);
        savedOpinion.getUsuario().setRoles(null);
        savedOpinion.getInmueble().setOpiniones(null);
        savedOpinion.getInmueble().setInmuebleFotoList(null);
        savedOpinion.getInmueble().getUbigeo().setInmuebleZonaList(null);
        savedOpinion.getInmueble().setCaracteristicaList(null);
        savedOpinion.getInmueble().setUsuario(null);
        return savedOpinion;
    }
}
