package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UbigeoRepository extends JpaRepository<Ubigeo, Long> {


    @Query(value = "SELECT * FROM ubigeos WHERE departamento =?1 AND provincia=?2 AND distrito =?3", nativeQuery = true)
    Ubigeo findUbigeoByDepartamentoAndProvinciaAndDistrito(String departamento, String provincia, String distrito);

}
