package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    @Query(value="SELECT * FROM opiniones WHERE usuario_id=?1", nativeQuery = true)
    List<Opinion> findByUsuario_Id (Long id);

    List<Opinion> deleteAllByInmueble_Id (Long id);
}
