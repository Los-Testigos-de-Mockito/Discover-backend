package servicesimpl;

import com.example.discoverbackend.dtos.DTOContactoUsuario;
import com.example.discoverbackend.dtos.ShowInmuebleResponse;
import com.example.discoverbackend.entities.*;
import com.example.discoverbackend.repositories.InmuebleFotoRepository;
import com.example.discoverbackend.repositories.InmuebleRepository;
import com.example.discoverbackend.services.CaracteristicaService;
import com.example.discoverbackend.servicesimpl.InmuebleServiceImpl;
import com.example.discoverbackend.servicesimpl.UsuarioServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InmuebleServiceImplTest {

    @Mock
    private InmuebleRepository inmuebleRepository;

    @Mock
    private InmuebleFotoRepository inmuebleFotoRepository;

    @Mock
    private UsuarioServiceImpl usuarioService;

    @InjectMocks
    private InmuebleServiceImpl inmuebleService;

    @Mock
    private CaracteristicaService caracteristicaService;

    @BeforeEach
    public void setUp() {
        inmuebleService = new InmuebleServiceImpl();
    }



    @Test
    public void testInmuebleCreation() {
        Usuario owner = new Usuario();
        Ubigeo ubigeo = new Ubigeo(1, "Distrito Test", "Provincia Test", "Departamento Test", null);
        Inmueble inmueble = new Inmueble("House", "No", "123 Main St", 1200.0, 3, 2, 4, 120, "New", "Nice place near park", owner, ubigeo);

        Assert.assertNotNull(inmueble);
        assertEquals("123 Main St", inmueble.getAddress());
        assertEquals(Double.valueOf(1200.0), inmueble.getPrice());
        assertEquals("House", inmueble.getPropertyType());
    }
/*
    @Test
    public void testListAllInmuebles() {
        Inmueble inmueble = new Inmueble();
        inmueble.setId(1L);
        when(inmuebleRepository.findAll()).thenReturn(Arrays.asList(inmueble));

        List<PrincipalInmueblesResponse> responses = inmuebleService.listAll();
        Assert.assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
        assertEquals(Long.valueOf(1L), responses.get(0).getId());
    }

*/
    @Test
    public void testListDataInmueble() {
        // inicialización
        Long id = 1L;

        // añadir usuario al Inmueble
        Usuario user = new Usuario();
        user.setId(123L); // asumimos el usuario con id 123

        // Creación de una nueva lista de InmuebleCaracteristica
        List<InmuebleCaracteristica> caracteristicaList = new ArrayList<>();

        // Crear un nuevo Inmueble e inicializar la lista de características
        Inmueble testInmueble = new Inmueble();
        testInmueble.setUsuario(user);
        testInmueble.setCaracteristicaList(caracteristicaList);

        //Datos de prueba
        InmuebleFoto inmuebleFoto1 = new InmuebleFoto();
        Foto testFoto1 = new Foto();
        testFoto1.setPhotoLink("photo1.com");
        inmuebleFoto1.setFoto(testFoto1);

        InmuebleFoto inmuebleFoto2 = new InmuebleFoto();
        Foto testFoto2 = new Foto();
        testFoto2.setPhotoLink("photo2.com");
        inmuebleFoto2.setFoto(testFoto2);

        List<InmuebleFoto> inmuebleFotos = Arrays.asList(inmuebleFoto1,  inmuebleFoto2);
        List<Opinion> opinionList = new ArrayList<>();
        Opinion opinion = new Opinion();
        opinion.setObservaciones("test observation");
        opinion.setCalificacion(5.0);
        opinionList.add(opinion);
        testInmueble.setOpiniones(opinionList);
        DTOContactoUsuario testContact = new DTOContactoUsuario(user.getId(), "name","apellido", "apellido","dni","telefono","email","dateAfiliation","dateBirth","linkFotoPerfil");

        // Simulación el retorno de un resultado
        Mockito.when(inmuebleRepository.findById(id)).thenReturn(Optional.of(testInmueble));
        Mockito.when(inmuebleFotoRepository.findByInmueble_Id(id)).thenReturn(inmuebleFotos);
        Mockito.when(usuarioService.listContactoUsuario(user.getId())).thenReturn(testContact);

        // execute
        ShowInmuebleResponse result = inmuebleService.listDataInmueble(id);

        //id++;

        // validate each field that the method sets data to on result then assert
        assertEquals(id, result.getId(),"El ID del inmueble no coincide");
        assertEquals(testInmueble.getAddress(), result.getAddress(), "La dirección del inmueble no coincide");

    }

    @Test
    public void testGetInmuebleOpinions(){
        Inmueble var1 = new Inmueble();
        Usuario usuario = new Usuario();
        Opinion opinion1 = new Opinion(usuario, var1, 5.0, "Test1");
        Opinion opinion2 = new Opinion(usuario, var1, 4.0, "Test2");
        List<Opinion> opinionList = Arrays.asList(opinion1, opinion2);
        var1.setOpiniones(opinionList);

        Assertions.assertTrue(opinionList.get(0).getObservaciones().equals("Test1"));
    }

}