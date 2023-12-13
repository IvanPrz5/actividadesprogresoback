package com.example.actividadesProgreso.Entity.Actividades.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.actividadesProgreso.Entity.Actividades.ArchivoActividadEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArchivoDTO {
    private Long id;
    private String nombre;
    private String fechaSubida;
    private Map<String, Object> usuario = new HashMap<>();
    private List<ArchivoDTO> archivoDepende = new ArrayList<>();

    /* public ArchivoDTO(ArchivoActividadEntity archivoActividad){
        this.id = archivoActividad.getId();
        this.nombre = archivoActividad.getNombre();
        this.fechaSubida = archivoActividad.getFechaSubida().toString();
        usuario.put("id", archivoActividad.getIdUsuario().getId());
        String nombre = archivoActividad.getIdUsuario().getNombre() + " " + archivoActividad.getIdUsuario().getApPaterno() + " " + archivoActividad.getIdUsuario().getApMaterno();
        usuario.put("descripcion", nombre);
        usuario.put("icon", archivoActividad.getIdUsuario().getIcon());
    } */

    public void convertDto(ArchivoActividadEntity archivoActividad){
        this.id = archivoActividad.getId();
        this.nombre = archivoActividad.getNombre();
        this.fechaSubida = archivoActividad.getFechaSubida().toString();
        usuario.put("id", archivoActividad.getIdUsuario().getId());
        String nombre = archivoActividad.getIdUsuario().getNombre() + " " + archivoActividad.getIdUsuario().getApPaterno();
        usuario.put("descripcion", nombre);
        usuario.put("icon", archivoActividad.getIdUsuario().getIcon());
    }

    public void agregarItemLista(ArchivoDTO archivoDTO){
        this.archivoDepende.add(archivoDTO);
    }
}
