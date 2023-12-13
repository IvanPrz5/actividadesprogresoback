package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Repository.Actividades.BienActividadRepository;

@Service
public class BienActividadService {

    @Autowired
    BienActividadRepository bienActividadRepository;
    
    public Optional<BienActividadEntity> getById(Long id){
        try {
            Optional<BienActividadEntity> bienActividad = bienActividadRepository.findById(id);
            return bienActividad;
        } catch (Exception e) {
            return null;
        }
    }

    // Revisar
    public List<BienActividadEntity> getByIdEmpresaQuery(Long idEmpresa){
        try {
            return bienActividadRepository.findByIdEmpresaQuery(idEmpresa);
        } catch (Exception e) {
            return null;
        }
    }

    public List<BienActividadEntity> getByIdEmpresaAndNullQuery(Long idEmpresa){
        try {
            return bienActividadRepository.findByIdEmpresaAndNullQuery(idEmpresa);
        } catch (Exception e) {
            return null;
        }
    }

    public List<GlobalDTO> getByStatus(){
        try {
            Sort sort = Sort.by("id").ascending();
            List<BienActividadEntity> bienActividad = bienActividadRepository.findByStatus(true, sort);
            return bienActividad.stream()
            .map(this::entityToDTO)
            .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public GlobalDTO entityToDTO(BienActividadEntity bienActividadEntity) {
        return new GlobalDTO(
                bienActividadEntity);
    }

    public List<BienActividadEntity> getByIdActividadAndStatus(Long idActividad, Long idEmpresa){
        try {
            return bienActividadRepository.findByIdActividadAndStatus(idActividad, idEmpresa);
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<BienActividadEntity> addRegister(BienActividadEntity var) {
        try {
            bienActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BienActividadEntity> editRegister(Long id, BienActividadEntity object) {
        Optional<BienActividadEntity> bienActividadId = bienActividadRepository.findById(id);

        if (bienActividadId.isPresent()) {
            bienActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BienActividadEntity> editStatus(Long id, BienActividadEntity object) {
        Optional<BienActividadEntity> bienActividadId = bienActividadRepository.findById(id);

        if (bienActividadId.isPresent()) {
            BienActividadEntity bienArchivoActividadEntity = bienActividadId.get();
            bienArchivoActividadEntity.setStatus(object.getStatus());
            bienActividadRepository.save(bienArchivoActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BienActividadEntity> apartarActividad(Long id){
        Optional<BienActividadEntity> bienActividadId = bienActividadRepository.findById(id);
        
        if(bienActividadId.isPresent()){
            BienActividadEntity bienActividadEntity = bienActividadId.get();
            bienActividadEntity.setStatus(false);
            bienActividadRepository.save(bienActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
