package com.example.actividadesProgreso.Controller.Clientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;
import com.example.actividadesProgreso.Entity.Clientes.DTO.ClientesDTO;
import com.example.actividadesProgreso.Service.Clientes.ClientesService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/Clientes")
public class ClientesController {
    @Autowired
    ClientesService clientesService;

    @GetMapping("/byEmpresa/{idEmpresa}")
    public List<ClientesDTO> findByIdEmpresaAndStatus(@PathVariable("idEmpresa") Long idEmpresa){
        return clientesService.findByIdEmpresaAndStatus(idEmpresa);
    }

    @PostMapping("/add")
    public ResponseEntity<ClientesEntity> addRegister(@RequestBody ClientesEntity object){
        return clientesService.addRegister(object);
    }
}
