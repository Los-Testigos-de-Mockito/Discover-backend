package servicesimpl;

import com.example.discoverbackend.dtos.InmuebleRequest;
import com.example.discoverbackend.entities.*;
import com.example.discoverbackend.repositories.*;
import com.example.discoverbackend.servicesimpl.InmuebleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InmuebleServiceImplUnitTest {

    @Mock
    private InmuebleRepository inmuebleRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private UbigeoRepository ubigeoRepository;
    @Mock
    private InmuebleFotoRepository inmuebleFotoRepository;
    @Mock
    private FotoRepository fotoRepository;
    @Mock
    private CaracteristicaRepository caracteristicaRepository;

    @InjectMocks
    private InmuebleServiceImpl inmuebleService;

    @Test
    void testSave() {
        // Crear datos de prueba
        InmuebleRequest request = new InmuebleRequest();
        request.setFoto(new ArrayList<>());
        request.setCaracteristicasIds(new ArrayList<>());

        Usuario usuario = new Usuario();
        Usuario expectedUsuario = new Usuario();
        Inmueble expectedInmueble = new Inmueble();
        expectedInmueble.setUsuario(expectedUsuario);


        Ubigeo ubigeo = new Ubigeo();
        Foto newFoto = new Foto("somePhotoLink");

        // Configurar mocks
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(ubigeoRepository.findUbigeoByDepartamentoAndProvinciaAndDistrito(any(),any(),any())).thenReturn(ubigeo);
        when(inmuebleRepository.save(any())).thenReturn(expectedInmueble);
        when(fotoRepository.save(any())).thenReturn(newFoto);
        when(inmuebleFotoRepository.save(any())).thenReturn(new InmuebleFoto(expectedInmueble, newFoto));
        when(caracteristicaRepository.findById(any())).thenReturn(Optional.of(new Caracteristica()));

        // Ejecutar el método a probar
        Inmueble resultInmueble = inmuebleService.save(request);

        // Verificar el resultado
        assertEquals(expectedInmueble, resultInmueble);

        // Verificar que los métodos mockeados fueron llamados
        verify(usuarioRepository).findById(any());
        verify(ubigeoRepository).findUbigeoByDepartamentoAndProvinciaAndDistrito(any(),any(),any());
        verify(inmuebleRepository).save(any());
        verify(fotoRepository, times(request.getFoto().size())).save(any());
        verify(inmuebleFotoRepository, times(request.getFoto().size())).save(any());
        verify(caracteristicaRepository, times(request.getCaracteristicasIds().size())).findById(any());
    }
}