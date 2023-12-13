package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.NotasActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.NotasActividadInterface;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.NotasActividadRepository;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;

@Service
public class NotasActividadService {

    @Autowired
    NotasActividadRepository notasActividadRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    public Optional<NotasActividadEntity> getById(Long id) {
        try {
            Optional<NotasActividadEntity> notasActividad = notasActividadRepository.findById(id);
            return notasActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NotasActividadInterface> getByIdActividadAndIdUsuario(Long idActividad){
        try {
            List<NotasActividadInterface> query = notasActividadRepository.findByIdActividadAndIdUsuario(idActividad); 
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<NotasActividadEntity> addRegister(NotasActividadEntity var) {
        try {
            notasActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<NotasActividadEntity> editRegister(Long id, NotasActividadEntity object) {
        Optional<NotasActividadEntity> notasActividadId = notasActividadRepository.findById(id);

        if (notasActividadId.isPresent()) {
            notasActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<NotasActividadEntity> editStatus(Long id, NotasActividadEntity object) {
        Optional<NotasActividadEntity> notasActividadId = notasActividadRepository.findById(id);

        if (notasActividadId.isPresent()) {
            NotasActividadEntity notasActividadEntity = notasActividadId.get();
            notasActividadEntity.setStatus(object.getStatus());
            notasActividadRepository.save(notasActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
