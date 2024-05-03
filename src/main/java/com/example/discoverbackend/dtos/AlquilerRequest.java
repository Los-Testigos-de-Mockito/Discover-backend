package com.example.discoverbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerRequest {

    private Long client_id;
    private Long inmueble_id;
    private Double price;
    private Date transactionDate;

}