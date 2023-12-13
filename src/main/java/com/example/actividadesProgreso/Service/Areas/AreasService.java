package com.example.actividadesProgreso.Service.Areas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Areas.AreasEntity;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.IdAndDescripcionInterface;
import com.example.actividadesProgreso.Repository.Areas.AreasRepository;


@Service
public class AreasService{
    
    @Autowired
    AreasRepository areasRepository;

    public List<AreasEntity> getAll(){
        return areasRepository.findAll();
    }

    public List<IdAndDescripcionInterface> getAreasByIdUsuario(Long id){
        try {
            List<IdAndDescripcionInterface> query = areasRepository.getAreas(id);
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> addRegister(AreasEntity var) {
        try {
            areasRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}