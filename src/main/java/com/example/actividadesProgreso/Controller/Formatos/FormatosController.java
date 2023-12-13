package com.example.actividadesProgreso.Controller.Formatos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.example.actividadesProgreso.Service.Formatos.FormatosService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/Formatos")
public class FormatosController {
    
    @Autowired
    FormatosService formatoService;

    @GetMapping("/generarFormato/{idActividad}")
    public ResponseEntity<Resource> jasperToPDF(@PathVariable("idActividad") Long idActividad){
        return formatoService.jasperToPDF(idActividad);
    }
}
