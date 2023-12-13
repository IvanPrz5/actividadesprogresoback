package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.StatusActividadEntity;
import com.example.actividadesProgreso.Repository.Actividades.StatusActividadRepository;

@Service
public class StatusActividadService {

    @Autowired
    StatusActividadRepository statusActividadRepository;

    public Optional<StatusActividadEntity> getById(Long id){
        try {
            Optional<StatusActividadEntity> statusActividad = statusActividadRepository.findById(id);
            return statusActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<StatusActividadEntity> getByStatus(Boolean status){
        try {
            List<StatusActividadEntity> statusActividad = statusActividadRepository.findByStatus(status);
            return statusActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<StatusActividadEntity> addRegister(StatusActividadEntity var) {
        try {
            statusActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<StatusActividadEntity> editRegister(Long id, StatusActividadEntity object) {
        Optional<StatusActividadEntity> statusActividadId = statusActividadRepository.findById(id);

        if (statusActividadId.isPresent()) {
            statusActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<StatusActividadEntity> editStatus(Long id, StatusActividadEntity object) {
        Optional<StatusActividadEntity> statusActividadId = statusActividadRepository.findById(id);

        if (statusActividadId.isPresent()) {
            StatusActividadEntity statusActividadEntity = statusActividadId.get();
            statusActividadEntity.setStatus(object.getStatus());
            statusActividadRepository.save(statusActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
