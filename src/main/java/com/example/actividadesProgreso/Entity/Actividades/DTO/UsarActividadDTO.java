package com.example.actividadesProgreso.Entity.Actividades.DTO;

import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsarActividadDTO {
    private String id;
    private String idBienActividad;
    private String nombre;
    private String descripcion;
    private String inicio;
    private String fin;

    public UsarActividadDTO(UsarActividadEntity usarActividadEntity){
        this.id = usarActividadEntity.getId().toString();
        this.idBienActividad = usarActividadEntity.getIdBienActividad().getId().toString();
        this.nombre = usarActividadEntity.getIdBienActividad().getDescripcion();
        this.descripcion = usarActividadEntity.getObservacionesSalida();
        this.inicio = usarActividadEntity.getInicio().toString();
        this.fin = usarActividadEntity.getFin().toString();
    }
}