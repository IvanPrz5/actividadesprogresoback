package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.TipoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Repository.Actividades.TipoActividadRepository;

@Service
public class TipoActividadService {

    @Autowired
    TipoActividadRepository tipoActividadRepository;

    public List<GlobalDTO> getByStatus() {
        try {
            Sort sort = Sort.by("id").ascending();
            List<TipoActividadEntity> tipoActividad = tipoActividadRepository.findByStatus(true, sort);

            return tipoActividad.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public GlobalDTO entityToDTO(TipoActividadEntity tipoActividadEntity) {
        return new GlobalDTO(
                tipoActividadEntity);
    }

    public Optional<TipoActividadEntity> getById(Long id) {
        try {
            Optional<TipoActividadEntity> tipoActividad = tipoActividadRepository.findById(id);
            return tipoActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<TipoActividadEntity> addRegister(TipoActividadEntity var) {
        try {
            tipoActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TipoActividadEntity> editRegister(Long id, TipoActividadEntity object) {
        Optional<TipoActividadEntity> tipoActividadId = tipoActividadRepository.findById(id);

        if (tipoActividadId.isPresent()) {
            tipoActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<TipoActividadEntity> editStatus(Long id, TipoActividadEntity object) {
        Optional<TipoActividadEntity> tipoActividadId = tipoActividadRepository.findById(id);

        if (tipoActividadId.isPresent()) {
            TipoActividadEntity tipoActividadEntity = tipoActividadId.get();
            tipoActividadEntity.setStatus(object.getStatus());
            tipoActividadRepository.save(tipoActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
