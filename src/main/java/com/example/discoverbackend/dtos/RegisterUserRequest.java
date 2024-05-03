package com.example.discoverbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String firstName;
    private String lastNameDad;
    private String lastNameMom;
    private String email;
    private String password;
    private String numPhone;
    private String dni;
    private String linkPhotoDni;
    private Date birthDate;
    private String linkPhotoProfile;
}
