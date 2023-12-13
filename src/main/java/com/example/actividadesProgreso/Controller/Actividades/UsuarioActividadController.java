package com.example.actividadesProgreso.Controller.Actividades;

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

import com.example.actividadesProgreso.Entity.Actividades.UsuarioActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.UsuarioActividadInterface;
import com.example.actividadesProgreso.Service.Actividades.UsuarioActividadService;

import java.util.List;

import javax.validation.Valid;
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/UsuarioActividad")
public class UsuarioActividadController {
    
    @Autowired
    UsuarioActividadService usuarioActividadService;

    @GetMapping("/byIdActividad/{id}")
    public List<UsuarioActividadInterface> byIdActividad(@PathVariable("id") Long id){
        return usuarioActividadService.getByIdActividad(id);
    }

    @PostMapping("/add")
    public ResponseEntity<UsuarioActividadEntity> addRegister(@Valid @RequestBody UsuarioActividadEntity object){
        return usuarioActividadService.addRegister(object);
    }

    @PutMapping("/editStatus/{idActividad}/{idUsuario}")
    public List<UsuarioActividadEntity> editStatus(@PathVariable("idActividad") Long idActividad, @PathVariable("idUsuario") Long idUsuario){
        return usuarioActividadService.editStatus(idActividad, idUsuario);
    }

    @PutMapping("/edit/{idActividad}/{idUserAct}")
    public List<UsuarioActividadEntity> editRegister(@PathVariable("idActividad") Long idActividad, 
        @PathVariable("idUserAct") Long idUserAct){
        return usuarioActividadService.editRegisterQuery(idActividad, idUserAct);
    }
} 
