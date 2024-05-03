package com.example.discoverbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerResponse {

    private Long id;
    private String location; //Ubicación
    private String fullNameOwner; //Nombre completo del propietario
    private Double price; //Precio alquiler
    private String transactionDate; //Fecha transacción
    private Boolean active; //Alquiler activo
    private Long property_id; //Inmueble ID
}
