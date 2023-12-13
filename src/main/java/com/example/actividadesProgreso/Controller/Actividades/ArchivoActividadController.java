package com.example.actividadesProgreso.Controller.Actividades;

import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.actividadesProgreso.Entity.Actividades.ArchivoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ArchivoDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Service.Actividades.ArchivoActividadService;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/ArchivoActividad")
public class ArchivoActividadController{
    @Autowired
    ArchivoActividadService archivoActividadService;

    @GetMapping("/{id}")
    public List<GlobalDTO> byId(@PathVariable("id") Long id){
        return archivoActividadService.getById(id);
    }

    @GetMapping("/byStatus")
    public List<GlobalDTO> byStatus(){
        return archivoActividadService.getByStatus(true);
    }

    @GetMapping("/byActividad/{id}")
    public List<GlobalDTO> byIdActividad(@PathVariable("id") Long id){
        return archivoActividadService.getByIdActividadAndStatus(id);
    }

    @GetMapping("/byIdActividadAndArchivoNull/{idActividad}")
    public List<ArchivoDTO> byIdActividadAndIdArchivoNull(@PathVariable("idActividad") Long idActividad){
        return archivoActividadService.byIdActividadAndArchivoNull(idActividad);
    }

    /* @GetMapping("/byIdActividadAndIdArchivo/{idActividad}/{idArchivo}")
    public List<ArchivoDTO> byIdActividadAndIdArchivo(@PathVariable("idActividad") Long idActividad, @PathVariable("idArchivo") Long idArchivo){
        return archivoActividadService.byIdActividadAndIdArchivoActividad(idActividad, idArchivo);
    } */

    // --------------------------  PostMapping  -------------------------------
    @PostMapping("/addasdasdas")
    public ResponseEntity<ArchivoActividadEntity> addRegister(@Valid @RequestBody ArchivoActividadEntity object){
        return archivoActividadService.addRegister(object);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody ArchivoActividadEntity object){
        return archivoActividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ArchivoActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody ArchivoActividadEntity object){
        return archivoActividadService.editStatus(id, object);
    }

    /* ------- ARCHIVO ------- */
    @PostMapping("/add")
    public String guardarArchivo(@RequestPart("file") List<MultipartFile> multipartFile, @RequestPart("doc") ArchivoActividadEntity doc){
        return archivoActividadService.guardarArchivo(multipartFile, doc);
    }

    @PostMapping("/contestar")
    public String constertarArchivo(@RequestPart("file") List<MultipartFile> multipartFile, @RequestPart("doc") ArchivoActividadEntity doc){
        return archivoActividadService.contestarAlArchivo(multipartFile, doc);
    }

    @GetMapping("/descarga/{id}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable("id") Long id){
        return archivoActividadService.descargarArchivo(id);
    }
}