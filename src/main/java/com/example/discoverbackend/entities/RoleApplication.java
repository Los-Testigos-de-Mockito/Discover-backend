package com.example.discoverbackend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class RoleApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<RoleUser> users;

    public RoleApplication(String name) {
        this.name = name;
    }
}
