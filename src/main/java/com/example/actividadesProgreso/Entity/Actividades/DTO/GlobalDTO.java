package com.example.actividadesProgreso.Entity.Actividades.DTO;

import com.example.actividadesProgreso.Entity.Actividades.ArchivoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.JerarquiaActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.TipoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalDTO {
    private String id;
    private String descripcion;
    private String icon;
    private String nombreUsuario;
    private String nombreArchivo;

    public GlobalDTO(TipoActividadEntity tipoActividad){
        this.id = tipoActividad.getId().toString();
        this.descripcion = tipoActividad.getDescripcion();
        this.icon = "No Aplica";
        this.nombreUsuario = "No Aplica";
        this.nombreArchivo = "No Aplica";
    }

    public GlobalDTO(JerarquiaActividadEntity jerarquiaActividad){
        this.id = jerarquiaActividad.getId().toString();
        this.descripcion = jerarquiaActividad.getDescripcion();
        this.icon = "No Aplica";
        this.nombreUsuario = "No Aplica";
        this.nombreArchivo = "No Aplica";
    }

    public GlobalDTO(BienActividadEntity bienActividad){
        this.id = bienActividad.getId().toString();
        this.descripcion = bienActividad.getDescripcion();
        this.icon = bienActividad.getIcon();
        this.nombreUsuario = "No Aplica";
        this.nombreArchivo = "No Aplica";
    }

    public GlobalDTO(UsuariosEntity usuarios){
        this.id = usuarios.getId().toString();
        String nombreCompleto = usuarios.getNombre() + " " + usuarios.getApPaterno();
        this.descripcion = nombreCompleto;
        this.icon = usuarios.getIcon();
        this.nombreUsuario = "No Aplica";
        this.nombreArchivo = "No Aplica";
    }

    public GlobalDTO(ArchivoActividadEntity archivo){
        this.id = archivo.getId().toString();
        this.descripcion = archivo.getFechaSubida().toString();
        this.icon = archivo.getIcon();
        String nombreCompleto = archivo.getIdUsuario().getNombre() + " " + archivo.getIdUsuario().getApPaterno();
        this.nombreUsuario = nombreCompleto;
        this.nombreArchivo = archivo.getNombre();
    }

    public GlobalDTO(UsarActividadEntity usarActividad){
        this.id = "No Aplica";
        this.descripcion = "No Aplica";
        this.icon = "No Aplica";
        this.nombreUsuario = usarActividad.getInicio().toString();
        this.nombreArchivo = usarActividad.getFin().toString();
    }
}
