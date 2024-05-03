package com.example.discoverbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpinionRequest {

    private Long user_id;
    private Long property_id;
    private String observation;
    private Double qualification;
}
