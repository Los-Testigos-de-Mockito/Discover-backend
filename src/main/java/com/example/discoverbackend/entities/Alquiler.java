package com.example.discoverbackend.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="alquiler")
@Data
@NoArgsConstructor
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario client;

    @ManyToOne
    @JoinColumn(name = "inmueble_id")
    private Inmueble inmueble;

    private Double price;
    private Date transactionDate;
    private Boolean activate;

    public Alquiler(Usuario client, Inmueble inmueble, Double price, Date transactionDate, Boolean activate) {
        this.client = client;
        this.inmueble = inmueble;
        this.price = price;
        this.transactionDate = transactionDate;
        this.activate = activate;
    }
}
