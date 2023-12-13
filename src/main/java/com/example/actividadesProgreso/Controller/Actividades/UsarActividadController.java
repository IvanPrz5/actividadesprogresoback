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

import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.UsarActividadDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.UsarActividadDTO2;
import com.example.actividadesProgreso.Service.Actividades.UsarActividadService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/UsarActividad")
public class UsarActividadController {

    @Autowired
    UsarActividadService usarActividadService;
    
    @GetMapping("/{id}")
    public Optional<UsarActividadEntity> byId(@PathVariable("id") Long id){
        return usarActividadService.getById(id);
    }

    @GetMapping("/totalRecursos/{idUsuario}/{idEmpresa}")
    public List<String> getTotalRecursos(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa){
        return usarActividadService.getTotalRecursos(idUsuario, idEmpresa);
    }

    @GetMapping("/byActividad/{id}")
    public List<UsarActividadDTO> byIdActividadAndStatus(@PathVariable("id") Long id){
        return usarActividadService.getByIdActividadAndStatus(id);
    }

    @GetMapping("/byUsuarioEmpresa/{idUsuario}/{idEmpresa}")
    public List<UsarActividadDTO2> recursosByUsuario(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa){
        return usarActividadService.getRecursosByUsuario(idUsuario, idEmpresa);
    }

    @GetMapping("/byFecha/{inicio}/{fin}/{id}")
    public List<Object> byFecha(@PathVariable("inicio") String inicio, @PathVariable("fin") String fin, @PathVariable("id") Long idBienActividad){
        return usarActividadService.fechaDisponible(inicio, fin, idBienActividad);
    }

    @GetMapping("/inicioFin/{id}")
    public List<GlobalDTO> byFecha(@PathVariable("id") Long idBienActividad){
        return usarActividadService.getByIdBienActividadAndStatus(idBienActividad);
    }

    @PostMapping("/add")
    public ResponseEntity<UsarActividadEntity> addRegister(@Valid @RequestBody UsarActividadEntity object){
        return usarActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsarActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody UsarActividadEntity object){
        return usarActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<UsarActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody UsarActividadEntity object){
        return usarActividadService.editStatus(id, object);
    }

    @PutMapping("/entregarRecurso/{id}")
    public ResponseEntity<String> entregarRecurso(@PathVariable("id") Long id, @RequestBody UsarActividadEntity object){
        return usarActividadService.entregarRecurso(id, object);
    }
}
