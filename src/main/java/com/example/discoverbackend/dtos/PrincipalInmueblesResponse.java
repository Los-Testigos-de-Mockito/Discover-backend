package com.example.discoverbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class PrincipalInmueblesResponse {

    private Long id;
    private String linkPhotoUser;
    private String fullName;
    private String province;
    private String department;
    private String district;
    private String linkPhotoProperty;
    private Double price;
    private Integer squareMeter;
    private Integer numGuest;
    private Integer numBedrooms;
    private Integer numBathrooms;
    private String description;
    private String properType;
    private String sharedRoom;
    private List<TipoCaracteristica> caracteristicaList;
}
