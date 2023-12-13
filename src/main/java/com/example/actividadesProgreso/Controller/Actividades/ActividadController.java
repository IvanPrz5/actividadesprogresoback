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

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ActividadDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ActividadDTO2;
import com.example.actividadesProgreso.Service.Actividades.ActividadService;

import java.util.List;
import javax.validation.Valid;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/Actividad")
public class ActividadController {
    
    @Autowired
    ActividadService actividadService;

    /* @GetMapping("/{id}")
    public Optional<ActividadEntity> byId(@PathVariable("id") Long id){
        return actividadService.getById(id);
    } */

    @GetMapping("/{id}")
    public List<ActividadDTO> byIdAndStatus(@PathVariable("id") Long id){
        return actividadService.getByIdAndStatus(id, true);
    }

    @GetMapping("/totalActividades/{idUsuario}/{idEmpresa}")
    public List<String> cuentaActividades(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa){
        return actividadService.cuentaActividades(idUsuario, idEmpresa);
    }

    @GetMapping("/terminanHoy/finalizadas/{idUsuario}/{idEmpresa}")
    public List<ActividadDTO2> consultaTerminanHoyAndVencidas(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idActividad){
        return actividadService.consultaTerminanHoyAndVencidas(idUsuario, idActividad);
    }

    @GetMapping("/diario/asigne/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaAsigneDiaria(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idActividad,
        @PathVariable("status") Boolean status){
        return actividadService.consultaAsigneDiaria(idUsuario, idActividad, status);
    }

    @GetMapping("/semanal/asigne/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaAsigneSemanal(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("status") Boolean status){
        return actividadService.consultaAsigneSemanal(idUsuario, idEmpresa, status);
    }

    @GetMapping("/mensual/asigne/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaAsigneMensual(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("status") Boolean status){
        return actividadService.consutalAsigneMensual(idUsuario, idEmpresa, status);
    }

    @GetMapping("/diario/asignaron/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaAsignaronDiaria(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idActividad,
        @PathVariable("status") Boolean status){
        return actividadService.consultaAsignaronDiaria(idUsuario, idActividad, status);
    }

    @GetMapping("/semanal/asignaron/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaAsignaronSemanal(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("status") Boolean status){
        return actividadService.consultaAsignaronSemanal(idUsuario, idEmpresa, status);
    }

    @GetMapping("/mensual/asignaron/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaAsignaronMensual(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa, 
        @PathVariable("status") Boolean status){
        return actividadService.consultaAsignaronMensual(idUsuario, idEmpresa, status);
    }

    @GetMapping("/diario/todas/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaDiariaTodas(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("status") Boolean status){
        return actividadService.consultaDiariaTodas(idUsuario, idEmpresa, status);
    }

    @GetMapping("/semanal/todas/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaSemanalTodas(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("status") Boolean status){
        return actividadService.consultaSemanalTodas(idUsuario, idEmpresa, status);
    }

    @GetMapping("/mensual/todas/{idUsuario}/{idEmpresa}/{status}")
    public List<ActividadDTO2> consultaMensualTodas(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("status") Boolean status){
        return actividadService.consutalMensualTodas(idUsuario, idEmpresa, status);
    }

    @GetMapping("/porDia/{idUsuario}/{inicio}/{idEmpresa}")
    public List<ActividadDTO2> getActividadesPorDia(@PathVariable("idUsuario") Long idUsuario, @PathVariable("inicio") String inicio, 
        @PathVariable("idEmpresa") Long idEmpresa){
        return actividadService.getActividadesPorDia(idUsuario, inicio, idEmpresa);
    }

    @GetMapping("/porMes/{idUsuario}/{idEmpresa}/{inicio}/{fin}")
    public List<ActividadDTO2> getActividadesEnMes(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEmpresa") Long idEmpresa,
        @PathVariable("inicio") String inicio, @PathVariable("fin") String fin){
        return actividadService.getActividadesEnMes(idUsuario, idEmpresa, inicio, fin);
    }

    @GetMapping("/addMensuales")
    public ResponseEntity<String> getActividadesByIsMensualTrue(@RequestBody List<ActividadEntity> object){
        return actividadService.addRegister2(object);
    }

    // PostMapping
    @PostMapping("/add")
    public ResponseEntity<String> addRegister(@Valid @RequestBody ActividadEntity object){
        return actividadService.addRegister(object);
    }

    // PutMapping
    @PutMapping("/editar/{id}")
    public ResponseEntity<ActividadEntity> editRegister(@Valid @PathVariable("id") Long id, @RequestBody ActividadEntity object){
        return actividadService.editRegister(id, object);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ActividadEntity> editStatus(@PathVariable("id") Long id, @RequestBody ActividadEntity object){
        return actividadService.editStatus(id, object);
    }
}
