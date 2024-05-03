package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tipos_caracteristicas")
@Data
@NoArgsConstructor
public class TipoCaracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tipoCaracteristica")
    private List<Caracteristica> caracteristicas;

    public TipoCaracteristica(String name) {
        this.name = name;
    }
}
