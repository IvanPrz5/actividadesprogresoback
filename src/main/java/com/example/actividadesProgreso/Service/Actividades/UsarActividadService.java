package com.example.actividadesProgreso.Service.Actividades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.UsarActividadDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.UsarActividadDTO2;
import com.example.actividadesProgreso.Entity.Actividades.Interface.UsarActividadInterface;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.BienActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.UsarActividadRepository;
import com.example.actividadesProgreso.Service.Actividades.Utils.UsarActividadUtils;

@Service
public class UsarActividadService {

    @Autowired
    UsarActividadRepository usarActividadRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    BienActividadRepository bienActividadRepository;

    @Autowired
    UsarActividadUtils usarActividadUtils;

    public List<String> getTotalRecursos(Long idUsuario, Long idEmpresa){
        try {
            return usarActividadRepository.getTotalRecursos(idUsuario, idEmpresa);
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<UsarActividadEntity> getById(Long id){
        try {
            Optional<UsarActividadEntity> actividad = usarActividadRepository.findById(id);
            return actividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object> fechaDisponible(String inicio, String fin, Long idBienActividad){
        try {
            // List<UsarActividadEntity> fecha = usarActividadRepository.fechaDisponible(fechaD);
            return usarActividadRepository.fechaDisponible(inicio, fin, idBienActividad);
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsarActividadDTO2> getRecursosByUsuario(Long id, Long idEmpresa){
        try {
            List<UsarActividadInterface> usarActividadInterfaces = usarActividadRepository.getRecursosByUsuario(id, idEmpresa);
            List<UsarActividadDTO2> listaUsarActividad = new ArrayList<>();
            for(int i=0; i<usarActividadInterfaces.size(); i++){
                UsarActividadDTO2 usarActividadDTO2 = new UsarActividadDTO2();
                usarActividadDTO2.convertDto(usarActividadInterfaces.get(i));
                listaUsarActividad.add(usarActividadDTO2);
                usarActividadUtils.bienes(usarActividadInterfaces, listaUsarActividad, id, i);
                usarActividadUtils.usuariosAsigno2(usarActividadInterfaces, listaUsarActividad, id, i);
            }
            return listaUsarActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsarActividadDTO> getByIdActividadAndStatus(Long id){
        try {
            Optional<ActividadEntity> actividadId = actividadRepository.findById(id);
            ActividadEntity idActividad = actividadId.get();
            List<UsarActividadEntity> actividad = usarActividadRepository.findByIdActividadAndStatus(idActividad, true);
            return actividad.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

     public UsarActividadDTO entityToDTO(UsarActividadEntity usarActividadEntity) {
        return new UsarActividadDTO(
                usarActividadEntity);
    }

    public List<GlobalDTO> getByIdBienActividadAndStatus(Long id) {
        try {
            Optional<BienActividadEntity> bienActividad = bienActividadRepository.findById(id);
            BienActividadEntity idBienAct = bienActividad.get();
            List<UsarActividadEntity> usarActividad = usarActividadRepository.findByIdBienActividadAndStatus(idBienAct, true);
            return usarActividad.stream()
                    .map(this::entityToGlobalDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public GlobalDTO entityToGlobalDTO(UsarActividadEntity usarActividadEntity) {
        return new GlobalDTO(
                usarActividadEntity);
    }

    public ResponseEntity<UsarActividadEntity> addRegister(UsarActividadEntity var) {
        try {
            usarActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UsarActividadEntity> editRegister(Long id, UsarActividadEntity object) {
        Optional<UsarActividadEntity> usarActividadId = usarActividadRepository.findById(id);

        if (usarActividadId.isPresent()) {
            usarActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UsarActividadEntity> editStatus(Long id, UsarActividadEntity object) {
        Optional<UsarActividadEntity> usarActividadId = usarActividadRepository.findById(id);

        if (usarActividadId.isPresent()) {
            UsarActividadEntity usarActividadEntity = usarActividadId.get();
            usarActividadEntity.setStatus(object.getStatus());
            usarActividadRepository.save(usarActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> entregarRecurso(Long id, UsarActividadEntity object) {
        Optional<UsarActividadEntity> usarActividadId = usarActividadRepository.findById(id);

        if (usarActividadId.isPresent()) {
            LocalDateTime fecha = LocalDateTime.now();
            LocalDateTime fechaEntrega = fecha.minusHours(1);
            UsarActividadEntity usarActividadEntity = usarActividadId.get();
            usarActividadEntity.setFechaEntrega(fechaEntrega);
            usarActividadEntity.setObservacionesEntrega(object.getObservacionesEntrega());
            usarActividadEntity.setStatus(object.getStatus());
            usarActividadRepository.save(usarActividadEntity);
            return new ResponseEntity<>("entrego", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
