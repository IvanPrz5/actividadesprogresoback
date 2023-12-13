package com.example.actividadesProgreso.Controller.Actividades;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.actividadesProgreso.Entity.Actividades.TipoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Service.Actividades.TipoActividadService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/TipoActividad")
public class TipoActividadController {
    
    @Autowired
    TipoActividadService tipoActividadService;

    @GetMapping("/byStatus")
    public List<GlobalDTO> byStatus(){
        return tipoActividadService.getByStatus();
    }

    @GetMapping("/{id}")
    public Optional<TipoActividadEntity> byId(@PathVariable("id") Long id){
        return tipoActividadService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<TipoActividadEntity> addRegister(@Valid @RequestBody TipoActividadEntity object){
        return tipoActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody TipoActividadEntity object){
        return tipoActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<TipoActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody TipoActividadEntity object){
        return tipoActividadService.editStatus(id, object);
    }
}
