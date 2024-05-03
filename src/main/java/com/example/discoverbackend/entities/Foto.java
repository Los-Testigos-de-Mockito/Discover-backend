package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fotos")
@Data
@NoArgsConstructor
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoLink;

    @OneToMany(mappedBy = "foto")
    private List<InmuebleFoto> inmuebleFotos;


    public Foto(String photoLink) {
        this.photoLink = photoLink;
    }
}
