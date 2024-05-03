package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.RoleApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleApplication, Long> {

    RoleApplication findByName(String name);
    boolean existsByName(String name);
}
