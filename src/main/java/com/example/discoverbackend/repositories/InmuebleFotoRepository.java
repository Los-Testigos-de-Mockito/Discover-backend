package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.InmuebleFoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InmuebleFotoRepository extends JpaRepository<InmuebleFoto, Long> {

    List<InmuebleFoto> findByInmueble_Id(Long id);
    List<InmuebleFoto> findByFoto_Id(Long id);

    List<InmuebleFoto> deleteAllByInmueble_Id (Long id);
}
