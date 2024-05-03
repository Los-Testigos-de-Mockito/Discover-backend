package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.RoleApplication;
import com.example.discoverbackend.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    private static final String[] DEFAULT_ROLES = {"USER", "ADMIN"};

    public void seedRol() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            if(!roleRepository.existsByName(name)) {
                roleRepository.save(new RoleApplication().withName(name));
            }
        } );

    }
}
