package com.example.actividadesProgreso.Controller.Actividades;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividadesProgreso.Entity.Actividades.JerarquiaActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Service.Actividades.JerarquiaActividadService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/JerarquiaActividad")
public class JerarquiaActividadController{
    
    @Autowired
    JerarquiaActividadService jerarquiaActividadService;

    @GetMapping("/byStatus")
    public List<GlobalDTO> byStatus(){
        return jerarquiaActividadService.getByStatus();
    }

    @GetMapping("/{id}")
    public Optional<JerarquiaActividadEntity> byId(@PathVariable("id") Long id){
        return jerarquiaActividadService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<JerarquiaActividadEntity> addRegister(@Valid @RequestBody JerarquiaActividadEntity object){
        return jerarquiaActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JerarquiaActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody JerarquiaActividadEntity object){
        return jerarquiaActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<JerarquiaActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody JerarquiaActividadEntity object){
        return jerarquiaActividadService.editStatus(id, object);
    }
}
