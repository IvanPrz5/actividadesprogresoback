package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.ListaActividadEntity;
import com.example.actividadesProgreso.Repository.Actividades.ListaActividadRepository;

@Service
public class ListaActividadService {

    @Autowired
    ListaActividadRepository listaActividadRepository;

    public Optional<ListaActividadEntity> getById(Long id){
        try {
            Optional<ListaActividadEntity> listaActividad = listaActividadRepository.findById(id);
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ListaActividadEntity> getByStatus(Boolean status){
        try {
            List<ListaActividadEntity> listaActividad = listaActividadRepository.findByStatus(status);
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<ListaActividadEntity> addRegister(ListaActividadEntity var) {
        try {
            listaActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ListaActividadEntity> editRegister(Long id, ListaActividadEntity object) {
        Optional<ListaActividadEntity> listaActividadId = listaActividadRepository.findById(id);

        if (listaActividadId.isPresent()) {
            listaActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ListaActividadEntity> editStatus(Long id, ListaActividadEntity object) {
        Optional<ListaActividadEntity> listaActividadId = listaActividadRepository.findById(id);

        if (listaActividadId.isPresent()) {
            ListaActividadEntity listaActividadEntity = listaActividadId.get();
            listaActividadEntity.setStatus(object.getStatus());
            listaActividadRepository.save(listaActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
