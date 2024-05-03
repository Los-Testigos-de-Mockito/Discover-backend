package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "inmuebles")
@Data
@NoArgsConstructor
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyType;
    private String sharedRoom;
    private String address;
    private Double price;
    private Integer numBedrooms;
    private Integer numBathrooms;
    private Integer numGuests;
    private Integer squareMeter;
    private String timeAntiquity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ubigeo_id")
    private Ubigeo ubigeo;

    @OneToMany(mappedBy = "inmueble")
    private List<Opinion> opiniones;
    @OneToMany(mappedBy = "inmueble")
    private List<InmuebleFoto> inmuebleFotoList;
    @OneToMany(mappedBy = "inmueble")
    private List<InmuebleCaracteristica> caracteristicaList;


    public Inmueble(String propertyType, String sharedRoom, String address, Double price, Integer numBedrooms, Integer numBathrooms, Integer numGuests, Integer squareMeter, String timeAntiquity, String description, Usuario usuario, Ubigeo ubigeo) {
        this.propertyType = propertyType;
        this.sharedRoom = sharedRoom;
        this.address = address;
        this.price = price;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.numGuests = numGuests;
        this.squareMeter = squareMeter;
        this.timeAntiquity = timeAntiquity;
        this.description = description;
        this.usuario = usuario;
        this.ubigeo = ubigeo;
    }
}
