package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    Foto findByPhotoLink(String foto);


}
