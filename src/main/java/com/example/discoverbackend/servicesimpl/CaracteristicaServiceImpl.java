package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.repositories.CaracteristicaRepository;
import com.example.discoverbackend.services.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicaServiceImpl implements CaracteristicaService {

    @Autowired
    CaracteristicaRepository caracteristicaRepository;
    @Override
    public List<String> getCaracteristicaByTipoCaracteristicaId(Long id) {
        return caracteristicaRepository.getCaracteristicaByTipoCaracteristicaId(id);
    }
}
