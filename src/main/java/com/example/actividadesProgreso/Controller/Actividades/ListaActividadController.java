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

import com.example.actividadesProgreso.Entity.Actividades.ListaActividadEntity;
import com.example.actividadesProgreso.Service.Actividades.ListaActividadService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/ListaActividad")
public class ListaActividadController {

    @Autowired
    ListaActividadService listaActividadService;
    
    @GetMapping("/{id}")
    public Optional<ListaActividadEntity> byId(@PathVariable("id") Long id){
        return listaActividadService.getById(id);
    }

    @GetMapping("/byStatus")
    public List<ListaActividadEntity> byStatus(){
        return listaActividadService.getByStatus(true);
    }

    @PostMapping("/add")
    public ResponseEntity<ListaActividadEntity> addRegister(@Valid @RequestBody ListaActividadEntity object){
        return listaActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody ListaActividadEntity object){
        return listaActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ListaActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody ListaActividadEntity object){
        return listaActividadService.editStatus(id, object);
    }
}
