package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByClient_Id (Long id);

    List<Alquiler> deleteAllByInmueble_Id(Long id);
}
