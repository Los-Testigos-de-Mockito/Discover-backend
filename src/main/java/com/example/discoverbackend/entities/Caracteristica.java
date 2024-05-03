package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="caracteristicas")
@Data
@NoArgsConstructor
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String icon;

    @ManyToOne
    @JoinColumn(name = "tipo_caracteristica_id")
    private TipoCaracteristica tipoCaracteristica;

    public Caracteristica(String name, TipoCaracteristica tipoCaracteristica,String icon) {
        this.name = name;
        this.icon = icon;
        this.tipoCaracteristica = tipoCaracteristica;
    }
}
