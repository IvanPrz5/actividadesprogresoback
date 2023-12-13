package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.JerarquiaActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Repository.Actividades.JerarquiaActividadRepository;

@Service
public class JerarquiaActividadService{

    @Autowired
    JerarquiaActividadRepository jerarquiaActividadRepository;

    public List<GlobalDTO> getByStatus() {
        try {
            Sort sort = Sort.by("id").ascending();
            List<JerarquiaActividadEntity> jerarquiaActividad = jerarquiaActividadRepository.findByStatus(true, sort);

            return jerarquiaActividad.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public GlobalDTO entityToDTO(JerarquiaActividadEntity jerarquiaActividadEntity) {
        return new GlobalDTO(
                jerarquiaActividadEntity);
    }

    public Optional<JerarquiaActividadEntity> getById(Long id){
        try {
            Optional<JerarquiaActividadEntity> jerarquiaActividad = jerarquiaActividadRepository.findById(id);
            return jerarquiaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<JerarquiaActividadEntity> addRegister(JerarquiaActividadEntity var) {
        try {
            jerarquiaActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<JerarquiaActividadEntity> editRegister(Long id, JerarquiaActividadEntity object) {
        Optional<JerarquiaActividadEntity> jerarquiaActividadId = jerarquiaActividadRepository.findById(id);

        if (jerarquiaActividadId.isPresent()) {
            jerarquiaActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<JerarquiaActividadEntity> editStatus(Long id, JerarquiaActividadEntity object) {
        Optional<JerarquiaActividadEntity> jerarquiaActividadId = jerarquiaActividadRepository.findById(id);

        if (jerarquiaActividadId.isPresent()) {
            JerarquiaActividadEntity jerarquiaArchivoActividadEntity = jerarquiaActividadId.get();
            jerarquiaArchivoActividadEntity.setStatus(object.getStatus());
            jerarquiaActividadRepository.save(jerarquiaArchivoActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
