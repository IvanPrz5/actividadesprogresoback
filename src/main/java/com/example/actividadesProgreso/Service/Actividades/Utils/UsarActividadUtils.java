package com.example.actividadesProgreso.Service.Actividades.Utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.UsarActividadDTO2;
import com.example.actividadesProgreso.Entity.Actividades.Interface.UsarActividadInterface;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Repository.Actividades.BienActividadRepository;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;

@Service
public class UsarActividadUtils {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    BienActividadRepository bienActividadRepository;

    public List<UsarActividadDTO2> bienes(List<UsarActividadInterface> actividadInterface, List<UsarActividadDTO2> listaUsarActividad, Long idUsuario, int i) {
        List<BienActividadEntity> bienActividadEntity = bienActividadRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Bien_Actividad(), true);
        for (int k = 0; k < bienActividadEntity.size(); k++) {
            listaUsarActividad.get(i).getBienActividad().put("id", bienActividadEntity.get(k).getId());
            listaUsarActividad.get(i).getBienActividad().put("descripcion", bienActividadEntity.get(k).getDescripcion());
            listaUsarActividad.get(i).getBienActividad().put("icon", bienActividadEntity.get(k).getIcon());
        }
        return listaUsarActividad;
    }

    public List<UsarActividadDTO2> usuariosAsigno2(List<UsarActividadInterface> actividadInterface, List<UsarActividadDTO2> listaUsarActividad, Long idUsuario, int i) {
        List<UsuariosEntity> usuarioInicio = usuariosRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Usuario_Asigno(), true);
        for (int k = 0; k < usuarioInicio.size(); k++) {
            listaUsarActividad.get(i).getUsuarioAsigno().put("id", usuarioInicio.get(k).getId());
            listaUsarActividad.get(i).getUsuarioAsigno().put("descripcion", usuarioInicio.get(k).getNombre() + " "  + usuarioInicio.get(k).getApPaterno());
        }
        return listaUsarActividad;
    }
}
