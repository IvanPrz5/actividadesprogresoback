package com.example.actividadesProgreso.Entity.Actividades.DTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadDTO {
    private Long id;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Map<Object, Object> empresa = new HashMap<>();
    private Map<Object, Object> tipoActividad = new HashMap<>();
    private Map<Object, Object> statusActividad = new HashMap<>();
    private Map<Object, Object> usuarioAsigno = new HashMap<>();
    private Map<Object, Object> usuarioTermino = new HashMap<>();
    private Map<Object, Object> jerarquiaActividad = new HashMap<>();
    private Map<Object, Object> clientes = new HashMap<>();

    public ActividadDTO(ActividadEntity actividad) {
        this.id = actividad.getId();
        this.descripcion = actividad.getDescripcion();
        this.fechaInicio = actividad.getFechaInicio();
        this.fechaFin = actividad.getFechaFin();
    }
}
