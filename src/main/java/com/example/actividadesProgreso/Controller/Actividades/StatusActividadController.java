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

import com.example.actividadesProgreso.Entity.Actividades.StatusActividadEntity;
import com.example.actividadesProgreso.Service.Actividades.StatusActividadService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/StatusActividad")
public class StatusActividadController {

    @Autowired
    StatusActividadService statusActividadService;

    @GetMapping("/{id}")
    public Optional<StatusActividadEntity> byId(@PathVariable("id") Long id){
        return statusActividadService.getById(id);
    }

    @GetMapping("/byStatus")
    public List<StatusActividadEntity> byStatus(Boolean status){
        return statusActividadService.getByStatus(status = true);
    }

    @PostMapping("/add")
    public ResponseEntity<StatusActividadEntity> addRegister(@Valid @RequestBody StatusActividadEntity object){
        return statusActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody StatusActividadEntity object){
        return statusActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<StatusActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody StatusActividadEntity object){
        return statusActividadService.editStatus(id, object);
    }
}
