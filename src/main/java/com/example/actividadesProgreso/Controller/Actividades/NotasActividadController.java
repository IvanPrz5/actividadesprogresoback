package com.example.actividadesProgreso.Controller.Actividades;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.actividadesProgreso.Entity.Actividades.NotasActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.NotasActividadInterface;
import com.example.actividadesProgreso.Service.Actividades.NotasActividadService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/NotasActividad")
public class NotasActividadController{

    @Autowired
    NotasActividadService notasActividadService;

    @GetMapping("/{id}")
    public Optional<NotasActividadEntity> byId(@PathVariable("id") Long id){
        return notasActividadService.getById(id);
    }

    @GetMapping("byActividad/{idActividad}")
    public List<NotasActividadInterface> byStatus(@PathVariable("idActividad") Long idActividad){
        return notasActividadService.getByIdActividadAndIdUsuario(idActividad);
    }

    @PostMapping("/add")
    public ResponseEntity<NotasActividadEntity> addRegister(@Valid @RequestBody NotasActividadEntity object){
        return notasActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotasActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody NotasActividadEntity object){
        return notasActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<NotasActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody NotasActividadEntity object){
        return notasActividadService.editStatus(id, object);
    }
}
