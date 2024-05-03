package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastNameDad;
    private String lastNameMom;
    private String dni;
    private String telephone;
    private String email;
    private String password;
    private String linkPhotoDni;
    private String linkPhotoProfile;
    private Date dateBirth;
    private Date dateAffiliation;

    @OneToMany (mappedBy = "usuario", cascade={CascadeType.REMOVE})
    private List<Inmueble> inmuebles;

    @OneToMany (mappedBy = "usuario", cascade={CascadeType.REMOVE})
    private List<Opinion> opiniones;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<RoleUser> roles;

    public Usuario(String firstName, String lastNameDad, String lastNameMom, String dni, String telephone, String email, String password, String linkPhotoDni, String linkPhotoProfile, Date dateBirth, Date dateAffiliation, List<Inmueble> inmuebles) {
        this.firstName = firstName;
        this.lastNameDad = lastNameDad;
        this.lastNameMom = lastNameMom;
        this.dni = dni;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.linkPhotoDni = linkPhotoDni;
        this.linkPhotoProfile = linkPhotoProfile;
        this.dateBirth = dateBirth;
        this.dateAffiliation = dateAffiliation;
        this.inmuebles = inmuebles;
    }
}
