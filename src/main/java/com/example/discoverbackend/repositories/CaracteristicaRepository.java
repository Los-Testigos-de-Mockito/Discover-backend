package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica,Long> {

    @Query(value = "SELECT c.name FROM caracteristicas c WHERE c.tipo_caracteristica_id = ?1", nativeQuery = true)
    List<String> getCaracteristicaByTipoCaracteristicaId(Long id);
}
