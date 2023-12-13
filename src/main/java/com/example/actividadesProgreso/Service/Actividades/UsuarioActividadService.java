package com.example.actividadesProgreso.Service.Actividades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.UsuarioActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.UsuarioActividadInterface;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.UsuarioActividadRepository;

@Service
public class UsuarioActividadService {
    @Autowired
    UsuarioActividadRepository usuarioActividadRepository;

    @Autowired
    ActividadRepository actividadRepository;

    public ResponseEntity<UsuarioActividadEntity> addRegister(UsuarioActividadEntity var) {
        try {
            usuarioActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UsuarioActividadInterface> getByIdActividad(Long id){
        try {
            return usuarioActividadRepository.findByIdActividadQuery(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuarioActividadEntity> editStatus(Long idActividad, Long idUsuario){
        try {
            return usuarioActividadRepository.editStatusQuery(idActividad, idUsuario);
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<UsuarioActividadEntity> editRegisterQuery(Long idActividad, Long idUserAct){
        try {
            return usuarioActividadRepository.editRegisterQuery(idActividad, idUserAct);
        } catch (Exception e) {
            return null;
        }
    }
}
// Errores

// revisar la consulta todas y la semanal

/* 2	"Empleado"	"Paterno"	"empleado@gmail.com"	"Empleado"	"$2a$10$PLMc9qiJYDvRsmsTZcvQLu95.ZD2XqaFsLNPQ1py4osjSzqsHa.IS"
3	"Empleada"	"Paterno"	"empleada@gmail.com"	"Empleada"	"$2a$10$PLMc9qiJYDvRsmsTZcvQLu95.ZD2XqaFsLNPQ1py4osjSzqsHa.IS"
1	"Admin"	"Paterno"	"admin@gmail.com"	"Admin"	"$2a$10$PLMc9qiJYDvRsmsTZcvQLu95.ZD2XqaFsLNPQ1py4osjSzqsHa.IS"

"mdi-account-tie"
"mdi-account-tie-woman"
"mdi-account-supervisor"


//  Areas 

1	"Nomina"	true	1
2	"Sistemas"	true	1
//  Bien actividad 
4	"Laptop 2"	"mdi-laptop"	true
1	"Motocicleta"	"mdi-motorbike"	true
2	"Automovil 1"	"mdi-car"	true
3	"Laptop 1"	"mdi-laptop"	true */