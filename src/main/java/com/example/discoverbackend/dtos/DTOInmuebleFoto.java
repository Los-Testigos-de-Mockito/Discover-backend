package com.example.discoverbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOInmuebleFoto {

    //ESTE ES EL REQUEST RECIBE DEL FRONT,
    //insertar mueble fotos
    public List<Long> caracteristicasId;
   // public DATA DEL INMUBELE;
    public String departamento;
    public String provincia;
    public String distrito;
    public List<String> foto;

}
