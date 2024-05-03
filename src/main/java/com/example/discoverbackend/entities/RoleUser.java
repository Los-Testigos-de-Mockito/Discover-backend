package com.example.discoverbackend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role_user")
@Data
@NoArgsConstructor
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleApplication role;

    public RoleUser(Usuario user, RoleApplication role) {
        this.user = user;
        this.role = role;
    }
}
