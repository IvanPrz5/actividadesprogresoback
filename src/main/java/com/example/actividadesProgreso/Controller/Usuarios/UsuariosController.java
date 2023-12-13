package com.example.actividadesProgreso.Controller.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

import com.example.actividadesProgreso.Entity.Actividades.Interface.UsuariosInterface;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Entity.Usuarios.DTO.UsuariosDTO;
import com.example.actividadesProgreso.Service.Usuarios.UsuariosService;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/Usuarios")
public class UsuariosController {
    
    @Autowired
    UsuariosService usuariosService;

    @PutMapping("/password/{id}")
    public ResponseEntity<UsuariosEntity> cambiarContraseña(@PathVariable("id") Long idUsuario, @RequestBody UsuariosEntity usuarioParam){
        return usuariosService.cambiarContraseña(idUsuario, usuarioParam);
    }

    @PostMapping("/uploadImage")
    public String subirImagen(@RequestPart("file") MultipartFile multipartFile, @RequestPart("doc") UsuariosEntity doc){
        return usuariosService.subirImagen(multipartFile, doc);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<ByteArrayResource> getImagen(@PathVariable("id") Long id) {
        return usuariosService.getImagen(id);
    }

    @GetMapping("/byId/{id}")
    public List<UsuariosDTO> getById(@PathVariable("id") Long id){
        return usuariosService.getByIdAndStatus(id, true);
    }

    @GetMapping("/byEmpresa/{idEmpresa}")
    public List<UsuariosInterface> getByEmpresa(@PathVariable("idEmpresa") Long idEmpresa){
        return usuariosService.getUsuariosByEmpresa(idEmpresa);
    }

    @GetMapping("/byActividadAndEmpresa/{id}/{idEmpresa}")
    public List<UsuariosInterface> getUsuariosDisponibles(@PathVariable("id") Long idActividad, @PathVariable("idEmpresa") Long idEmpresa){
        return usuariosService.getUsuariosDisponibles(idActividad, idEmpresa);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UsuariosEntity> updatePerfil(@Valid @PathVariable("id") Long idUsuario, @RequestBody UsuariosEntity usuarioParam){
        return usuariosService.editUsuario(idUsuario, usuarioParam);
    }
}