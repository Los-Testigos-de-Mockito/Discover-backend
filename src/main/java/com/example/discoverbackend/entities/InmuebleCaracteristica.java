package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inmuebles_caracteristicas")
@Data
@NoArgsConstructor
public class InmuebleCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inmueble_id")
    private Inmueble inmueble;

    @ManyToOne
    @JoinColumn(name = "caracteristica_id")
    private Caracteristica caracteristica;

    public InmuebleCaracteristica(Inmueble inmueble, Caracteristica caracteristica) {
        this.inmueble = inmueble;
        this.caracteristica = caracteristica;
    }
}


