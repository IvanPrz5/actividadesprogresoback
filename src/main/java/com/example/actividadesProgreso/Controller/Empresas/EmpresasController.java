package com.example.actividadesProgreso.Controller.Empresas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividadesProgreso.Service.Empresas.EmpresasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/Empresas")
public class EmpresasController {
    
    @Autowired
    EmpresasService empresasService;

    @GetMapping("/byEmpresa/{id}")
    public List<Object> byIdEmpresa(@PathVariable("id") Long id){
        return empresasService.getBienesUnicos(id);
    }
    
}
