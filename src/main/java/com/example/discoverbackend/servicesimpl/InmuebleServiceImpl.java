package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.dtos.*;
import com.example.discoverbackend.dtos.TipoCaracteristica;
import com.example.discoverbackend.entities.*;
import com.example.discoverbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.discoverbackend.services.InmuebleService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InmuebleServiceImpl implements InmuebleService {

    @Autowired
    InmuebleRepository inmuebleRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UbigeoRepository ubigeoRepository;
    @Autowired
    CaracteristicaRepository caracteristicaRepository;
    @Autowired
    InmuebleCaracteristicaRepository inmuebleCaracteristicaRepository;
    @Autowired
    InmuebleFotoRepository inmuebleFotoRepository;
    @Autowired
    UsuarioServiceImpl usuarioService;
    @Autowired
    AlquilerRepository alquilerRepository;
    @Autowired
    OpinionRepository opinionRepository;

    @Autowired
    FotoRepository fotoRepository;

    public List<PrincipalInmueblesResponse> listAll(){
        List<PrincipalInmueblesResponse> propertiesResponse = new ArrayList<PrincipalInmueblesResponse>();
        List<Inmueble> properties = inmuebleRepository.findAll();
        for(Inmueble i: properties){
            Long id = i.getId();
            String linkPhotoUser = i.getUsuario().getLinkPhotoProfile();
            String fullName = i.getUsuario().getFirstName() + " " +i.getUsuario().getLastNameDad() + " " + i.getUsuario().getLastNameMom();
            String province = i.getUbigeo().getProvincia();
            String department = i.getUbigeo().getDepartamento();
            String district = i.getUbigeo().getDistrito();
            String linkPhotoProperty = i.getInmuebleFotoList().get(0).getFoto().getPhotoLink();
            Double price = i.getPrice();
            Integer squareMeter = i.getSquareMeter();
            Integer numGuest = i.getNumGuests();
            Integer numBedrooms = i.getNumBedrooms();
            Integer numBathrooms = i.getNumBathrooms();
            String description = i.getDescription();
            String properType = i.getPropertyType();
            String sharedRoom = i.getSharedRoom();
            List<TipoCaracteristica> caracteristicaList = new ArrayList<TipoCaracteristica>();
            for(InmuebleCaracteristica ic: i.getCaracteristicaList()){
                String featureType = ic.getCaracteristica().getTipoCaracteristica().getName();
                String featureName = ic.getCaracteristica().getName();
                caracteristicaList.add(new TipoCaracteristica(featureType, featureName));
            }

            propertiesResponse.add(new PrincipalInmueblesResponse(id,linkPhotoUser, fullName, province, department, district, linkPhotoProperty, price, squareMeter,numGuest, numBedrooms, numBathrooms, description, properType, sharedRoom, caracteristicaList));
        }
        return propertiesResponse;
    }
    public List<DTOIconCaracteristica> getInmuebleCharacteristics(Inmueble inmueble) {
        List<InmuebleCaracteristica> inmuebleCaracteristicas = inmueble.getCaracteristicaList();
        List<DTOIconCaracteristica> dtoIconCaracteristicas = new ArrayList<>();
        for (InmuebleCaracteristica inmuebleCaracteristica : inmuebleCaracteristicas) {
            Caracteristica caracteristica = inmuebleCaracteristica.getCaracteristica();
            dtoIconCaracteristicas.add(new DTOIconCaracteristica(caracteristica.getName(), caracteristica.getIcon()));
        }
        return dtoIconCaracteristicas;
    }
    public List<DTOOpinion> getInmuebleOpinions(Inmueble inmueble) {
        List<Opinion> inmuebleOpiniones = inmueble.getOpiniones();
        List<DTOOpinion> dtoOpinions = new ArrayList<>();
        for (Opinion opinion : inmuebleOpiniones) {
            dtoOpinions.add(new DTOOpinion(opinion.getObservaciones(), opinion.getCalificacion()));
        }
        return dtoOpinions;
    }
    public ShowInmuebleResponse listDataInmueble(Long id){
        Inmueble i = inmuebleRepository.findById(id).get();
        List<InmuebleFoto> inmuebleFotos = inmuebleFotoRepository.findByInmueble_Id(id);
        List<String> photoUrls = new ArrayList<>();
        for (InmuebleFoto inmuebleFoto : inmuebleFotos) {
            photoUrls.add(inmuebleFoto.getFoto().getPhotoLink());
        }

        List<DTOIconCaracteristica> listCaracteristaInmuebleIcons = getInmuebleCharacteristics(i);
        DTOContactoUsuario owner =usuarioService.listContactoUsuario(i.getUsuario().getId());
        List<DTOOpinion> listOpinions = getInmuebleOpinions(i);

        ShowInmuebleResponse showInmuebleResponse = new ShowInmuebleResponse(id, i.getAddress(), i.getTimeAntiquity(),photoUrls,i.getPropertyType(), i.getPrice(),i.getNumGuests(),listCaracteristaInmuebleIcons,owner,i.getUsuario().getLinkPhotoProfile(),i.getNumBedrooms(),i.getNumBathrooms(), i.getSquareMeter(),i.getDescription(),listOpinions);
        return showInmuebleResponse;
    }

    @Transactional
    public Inmueble save(InmuebleRequest inmueble){
        Usuario usuario = usuarioRepository.findById(inmueble.getUsuario_id()).get();
        Ubigeo ubigeo = ubigeoRepository.findUbigeoByDepartamentoAndProvinciaAndDistrito(inmueble.getDepartamento(), inmueble.getProvincia(), inmueble.getDistrito());
        Inmueble saveInmueble = inmuebleRepository.save(new Inmueble(inmueble.getPropertyType(), inmueble.getSharedRoom(), inmueble.getAddress(), inmueble.getPrice(), inmueble.getNumBedrooms(), inmueble.getNumBathrooms(), inmueble.getNumGuests(), inmueble.getSquareMeter(), inmueble.getTimeAntiquity(), inmueble.getDescription(),usuario, ubigeo));
        List<Foto> foto = new ArrayList<Foto>();
        for (String f: inmueble.getFoto()){
           Foto newFoto = fotoRepository.save(new Foto(f));
           foto.add(newFoto);
        }
        List<InmuebleFoto> inmuebleFotos = new ArrayList<InmuebleFoto>();
        for(Foto foto1 : foto){
           InmuebleFoto inmuebleFoto = inmuebleFotoRepository.save(new InmuebleFoto(saveInmueble,foto1));
           inmuebleFotos.add(inmuebleFoto);
        }
        for(Long c: inmueble.getCaracteristicasIds()){
           Caracteristica newCaracteristica = caracteristicaRepository.findById(c).get();
           inmuebleCaracteristicaRepository.save(new InmuebleCaracteristica(saveInmueble, newCaracteristica));
        }
        for (Long caracteristicaId : inmueble.getCaracteristicasIds()){
            saveInmueble.setInmuebleFotoList(null);
        }
        saveInmueble.getUsuario().setInmuebles(null);
        saveInmueble.getUsuario().setOpiniones(null);
        saveInmueble.getUsuario().setRoles(null);
        saveInmueble.setOpiniones(null);
        saveInmueble.getUbigeo().setInmuebleZonaList(null);
        return saveInmueble;
    }
    @Transactional
    public void delete(Long id, boolean forced) {
        alquilerRepository.deleteAllByInmueble_Id(id);
        inmuebleCaracteristicaRepository.deleteAllByInmueble_Id(id);
        opinionRepository.deleteAllByInmueble_Id(id);
        List<InmuebleFoto> inmuebleFotos = inmuebleFotoRepository.findByInmueble_Id(id);
        List<Foto> fotos = new ArrayList<Foto>();
        for(InmuebleFoto ifo: inmuebleFotos){
            Foto foto = ifo.getFoto();
            fotos.add(foto);
        }
        inmuebleFotoRepository.deleteAllByInmueble_Id(id);
        fotoRepository.deleteAll(fotos);
        Inmueble inmueble = inmuebleRepository.findById(id).get();
        inmuebleRepository.delete(inmueble);
    }

    public List<String> gettAllSharedRoom(){
        return inmuebleRepository.getSharedRoom();
    }

    @Override
    public List<String> getAllPropertiesTypes() {
        return inmuebleRepository.getPropertyType();
    }

}
