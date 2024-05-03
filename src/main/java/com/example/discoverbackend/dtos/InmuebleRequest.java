package com.example.discoverbackend.dtos;

import com.example.discoverbackend.entities.Ubigeo;
import com.example.discoverbackend.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InmuebleRequest {

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
    private Long usuario_id;
    private String departamento;
    private String provincia;
    private String distrito;
    private List<String> foto;
    private List<Long> caracteristicasIds;
}