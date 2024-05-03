package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="ubigeos")
@Data
@NoArgsConstructor
public class Ubigeo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idUbigeo;
    private String distrito;
    private String provincia;
    private String departamento;

    @OneToMany (mappedBy = "ubigeo")
    private List<Inmueble> inmuebleZonaList;


    public Ubigeo(Integer idUbigeo, String distrito, String provincia, String departamento, List<Inmueble> inmuebleZonaList) {
        this.idUbigeo = idUbigeo;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.inmuebleZonaList = inmuebleZonaList;
    }
}
