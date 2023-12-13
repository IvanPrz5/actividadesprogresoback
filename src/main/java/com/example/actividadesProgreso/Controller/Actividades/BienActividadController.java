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

import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Service.Actividades.BienActividadService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/BienActividad")
public class BienActividadController {
    
    @Autowired
    BienActividadService bienActividadService;

    @GetMapping("/{id}")
    public Optional<BienActividadEntity> byId(@PathVariable("id") Long id){
        return bienActividadService.getById(id);
    }

    @GetMapping("/byStatus")
    public List<GlobalDTO> byStatus(){
        return bienActividadService.getByStatus();
    }

    @GetMapping("/byIdEmpresa/{id}")
    public List<BienActividadEntity> byIdEmpresaQuery(@PathVariable("id") Long idEmpresa){
        return bienActividadService.getByIdEmpresaQuery(idEmpresa);
    }

    @GetMapping("/byIdEmpresaNull/{id}")
    public List<BienActividadEntity> byIdEmpresaAndNullQuery(@PathVariable("id") Long idEmpresa){
        return bienActividadService.getByIdEmpresaAndNullQuery(idEmpresa);
    }

    @GetMapping("/byIdActividad/{id}/{idEmpresa}")
    public List<BienActividadEntity> byIdActividadAndStatus(@PathVariable("id") Long id, @PathVariable("idEmpresa") Long idEmpresa){
        return bienActividadService.getByIdActividadAndStatus(id, idEmpresa);
    }

    @PostMapping("/add")
    public ResponseEntity<BienActividadEntity> addRegister(@Valid @RequestBody BienActividadEntity object){
        return bienActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BienActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody BienActividadEntity object){
        return bienActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<BienActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody BienActividadEntity object){
        return bienActividadService.editStatus(id, object);
    }

    @PutMapping("/apartar/{id}")
    public ResponseEntity<BienActividadEntity> apartarBien(@PathVariable("id") Long id){
        return bienActividadService.apartarActividad(id);
    }
}
