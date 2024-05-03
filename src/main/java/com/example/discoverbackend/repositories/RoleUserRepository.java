package com.example.discoverbackend.repositories;

import com.example.discoverbackend.entities.RoleUser;
import com.example.discoverbackend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {

    List<RoleUser> findAllByUser(Usuario usuario);
}
