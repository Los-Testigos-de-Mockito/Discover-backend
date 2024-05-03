package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inmuebles_fotos")
@Data
@NoArgsConstructor
public class InmuebleFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inmueble_id")
    private Inmueble inmueble;

    @ManyToOne
    @JoinColumn(name = "foto_id")
    private Foto foto;

    public InmuebleFoto(Inmueble inmueble, Foto foto) {
        this.inmueble = inmueble;
        this.foto = foto;
    }
}
