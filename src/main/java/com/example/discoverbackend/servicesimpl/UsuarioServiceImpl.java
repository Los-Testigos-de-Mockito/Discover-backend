package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.dtos.DTOContactoUsuario;
import com.example.discoverbackend.dtos.LogInResponse;
import com.example.discoverbackend.dtos.LoginRequest;
import com.example.discoverbackend.dtos.RegisterUserRequest;
import com.example.discoverbackend.entities.Inmueble;
import com.example.discoverbackend.entities.RoleApplication;
import com.example.discoverbackend.entities.RoleUser;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.repositories.RoleRepository;
import com.example.discoverbackend.repositories.RoleUserRepository;
import com.example.discoverbackend.repositories.UsuarioRepository;
import com.example.discoverbackend.security.JwtTokenUtil;
import com.example.discoverbackend.services.UsuarioService;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {


    private final AuthenticationManager authManager;
    UsuarioRepository usuarioRepository;

    RoleRepository roleRepository;

    RoleUserRepository roleUserRepository;

    PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public Usuario save(RegisterUserRequest usuario) {
        RoleApplication rol = roleRepository.findByName("USER");
        Usuario newUsuario = new Usuario(usuario.getFirstName(), usuario.getLastNameDad(), usuario.getLastNameMom(), usuario.getDni(), usuario.getNumPhone(), usuario.getEmail(), encoder.encode(usuario.getPassword()),usuario.getLinkPhotoDni(), usuario.getLinkPhotoProfile(), usuario.getBirthDate(), new Date(), null);
        Usuario savedUsuario = usuarioRepository.save(newUsuario);
        roleUserRepository.save(new RoleUser(savedUsuario, rol));
        List<RoleUser> roles = roleUserRepository.findAllByUser(savedUsuario);
        savedUsuario.setRoles(roles);
        for(RoleUser ru: savedUsuario.getRoles()){
            ru.setUser(null);
            ru.getRole().setUsers(null);
        }

        return savedUsuario;
    }

    public DTOContactoUsuario listContactoUsuario(Long id) {
        Usuario u = usuarioRepository.findById(id).get();
        String monthString = new String();
        String dayString = new String();
        String monthString1 = new String();
        String dayString1 = new String();

                String fullname = u.getFirstName()+" "+u.getLastNameDad()+" "+u.getLastNameMom();

                Integer year = u.getDateAffiliation().getYear() + 1900;
                Integer month = u.getDateAffiliation().getMonth() + 1; //2022 - 3 - 6     2022-03-06
                Integer day = u.getDateAffiliation().getDate();

                if (month<10){
                    monthString = "0"+month;

                }else if (month>=10){
                    monthString = month.toString();
                }
                if (day<10){
                    dayString="0"+day;
                }else if (day>=10) {
                    dayString=day.toString();
                }

                Integer year1 = u.getDateBirth().getYear()+1900;
                Integer month1= u.getDateBirth().getMonth() + 1;
                Integer day1= u.getDateBirth().getDate();
                if (month1<10){
                    monthString1 = "0"+month;

                }else if (month1>=10){
                    monthString1 = month.toString();
                }
                if (day1<10){
                    dayString1="0"+day1;
                }else if (day1>=10) {
                    dayString1=day1.toString();
                }

                String dateString = year + " - " + monthString + " - " + dayString;
                String dateStringBirth = year1+" - "+monthString1+" - "+dayString1;
                DTOContactoUsuario dtoContactoUsuario = new DTOContactoUsuario(id, u.getFirstName(), u.getLastNameDad(), u.getLastNameMom(), u.getDni(), u.getTelephone(), u.getEmail(), dateString, dateStringBirth, u.getLinkPhotoProfile());

        return dtoContactoUsuario;
    }

    @Override
    public LogInResponse login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).get();
        return new LogInResponse(jwt, usuario.getId());
    }


}
