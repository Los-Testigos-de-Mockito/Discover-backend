package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.dtos.AlquilerRequest;
import com.example.discoverbackend.dtos.AlquilerResponse;
import com.example.discoverbackend.entities.*;
import com.example.discoverbackend.repositories.AlquilerRepository;
import com.example.discoverbackend.repositories.InmuebleRepository;
import com.example.discoverbackend.repositories.UsuarioRepository;
import com.example.discoverbackend.services.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    AlquilerRepository alquilerRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    InmuebleRepository inmuebleRepository;

    public Alquiler createAlquiler(AlquilerRequest alquiler) {
        List<Alquiler> alquileresAnteriores = alquilerRepository.findByClient_Id(alquiler.getClient_id());

        for (Alquiler a : alquileresAnteriores) {
            if(a.getInmueble().getId().equals(alquiler.getInmueble_id()) && a.getActivate())
                throw new RuntimeException("Inmueble ya alquilado");
            a.setActivate(false);
            alquilerRepository.save(a);
        }
        Usuario usuario = usuarioRepository.findById(alquiler.getClient_id()).get();
        Inmueble inmueble = inmuebleRepository.findById(alquiler.getInmueble_id()).get();
        if(inmueble.getUsuario().getId().equals(usuario.getId()))
            throw new RuntimeException("Dueño no puede alquilar su propio inmueble");
        Alquiler newAlquiler = new Alquiler(usuario, inmueble, alquiler.getPrice(), alquiler.getTransactionDate(), true);
        Alquiler savedAlquiler = alquilerRepository.save(newAlquiler);

        savedAlquiler.getClient().setInmuebles(null);
        savedAlquiler.getClient().setOpiniones(null);
        for(RoleUser ru: savedAlquiler.getClient().getRoles()){
            ru.setUser(null);
            ru.getRole().setUsers(null);
        }

        savedAlquiler.getInmueble().getUbigeo().setInmuebleZonaList(null);
        savedAlquiler.getInmueble().setOpiniones(null);
        savedAlquiler.getInmueble().getUsuario().setInmuebles(null);
        savedAlquiler.getInmueble().getUsuario().setOpiniones(null);
        for(RoleUser ru: savedAlquiler.getInmueble().getUsuario().getRoles()){
            ru.setUser(null);
            ru.getRole().setUsers(null);
        }
        for(InmuebleCaracteristica ic: savedAlquiler.getInmueble().getCaracteristicaList()){
            ic.setInmueble(null);
            ic.getCaracteristica().getTipoCaracteristica().setCaracteristicas(null);
        }
        for (InmuebleFoto f : savedAlquiler.getInmueble().getInmuebleFotoList()) {
            f.setInmueble(null);
            f.getFoto().setInmuebleFotos(null);
        }
        return savedAlquiler;
    }

    @Override
    public List<AlquilerResponse> listAlquilerByUser(Long id) {
        List<AlquilerResponse> alquilerList = new ArrayList<>();
        List<Alquiler> alquileres = alquilerRepository.findByClient_Id(id);
        for (Alquiler a : alquileres) {
            String location = a.getInmueble().getAddress();
            String fullNameOwner = a.getInmueble().getUsuario().getFirstName()+" " + a.getInmueble().getUsuario().getLastNameDad()+ " " + a.getInmueble().getUsuario().getLastNameMom();
            Double price = a.getPrice();

            String monthString = new String();
            String dayString = new String();
            Integer year= a.getTransactionDate().getYear() + 1900;
            Integer month = a.getTransactionDate().getMonth()+ 1; //2022 - 3 - 6     2022-03-06
            Integer day = a.getTransactionDate().getDate();

            if (month<10){
                monthString = "0"+month;

            }else if (month>=10){
                monthString = month.toString();
            }
            if (day<10){
                dayString="0"+day;
            }else if (day>=10) {
                dayString=day.toString();
            }

            String transactionDate = year + " - "+monthString +" - " +dayString;
            Boolean active = a.getActivate();
            Long property_id = a.getInmueble().getId();

            alquilerList.add(new AlquilerResponse(a.getId() ,location, fullNameOwner, price, transactionDate, active, property_id));
        }
        return alquilerList;
    }

    @Override
    public Alquiler updateAlquiler(Long id) {
        Alquiler alquiler = alquilerRepository.findById(id).get();
        alquiler.setActivate(!alquiler.getActivate());
        Alquiler newAlquiler = alquilerRepository.save(alquiler);
        newAlquiler.getClient().setInmuebles(null);
        newAlquiler.getClient().setOpiniones(null);
        newAlquiler.getClient().setRoles(null);
        //newAlquiler.setInmueble(null);
        //newAlquiler.setClient(null);
        newAlquiler.getInmueble().getUbigeo().setInmuebleZonaList(null);
        newAlquiler.getInmueble().setUsuario(null);
        newAlquiler.getInmueble().setOpiniones(null);
        newAlquiler.getInmueble().setCaracteristicaList(null);
        newAlquiler.getInmueble().setInmuebleFotoList(null);
        return newAlquiler;
    }
}
