package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {
    //JPQL
    @Query(value = "SELECT DISTINCT propertyType FROM Inmueble")
    List<String> getPropertyType();

    @Query(value = "SELECT DISTINCT sharedRoom from Inmueble")
    List<String> getSharedRoom();
}
