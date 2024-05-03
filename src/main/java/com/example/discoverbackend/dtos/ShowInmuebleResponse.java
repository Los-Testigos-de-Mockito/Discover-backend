package com.example.discoverbackend.dtos;

import com.example.discoverbackend.entities.Caracteristica;
import com.example.discoverbackend.entities.InmuebleFoto;
import com.example.discoverbackend.entities.Ubigeo;
import com.example.discoverbackend.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.convert.ThreeTenBackPortConverters;

import java.util.List;

@Data
@AllArgsConstructor
public class ShowInmuebleResponse {
    private Long id;
    private String address;
    private String timeAntiquity;
    private List<String> inmuebleFotoList;
    private String typeProperty;
    private Double price;
    private Integer numGuests;
    private List<DTOIconCaracteristica> listCaracteristaInmuebleIcons;
    private DTOContactoUsuario UserContact;
    private String photoOwner;
    private Integer numBedrooms;
    private Integer numBathrooms;
    private Integer squareMeter;
    private String description;
    private List<DTOOpinion> listOpinions;
}
