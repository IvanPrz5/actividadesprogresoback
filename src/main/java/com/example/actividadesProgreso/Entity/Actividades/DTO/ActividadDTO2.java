package com.example.actividadesProgreso.Entity.Actividades.DTO;

import java.util.HashMap;
import java.util.Map;

import com.example.actividadesProgreso.Entity.Actividades.Interface.ActividadInterface;
import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActividadDTO2{
    private Long id;
    private String descripcion;
    private String fechaInicio;
    private String fechaFinalizoUser;
    private String fechaFin;
    private String tipo;
    private String prioridad;
    private String status;
    private String isMensual;

    private Map<String, Object> empresa = new HashMap<>();
    private Map<String, Object> jerarquiaActividad = new HashMap<>();
    private Map<String, Object> statusActividad = new HashMap<>();
    private Map<String, Object> tipoActividad = new HashMap<>();
    private Map<String, Object> usuarioAsigno = new HashMap<>();
    private Map<String, Object> usuarioTermino = new HashMap<>();
    private Map<String, Object> cliente = new HashMap<>();

    public void convertDto(ActividadInterface actividadInterface){
        this.id = actividadInterface.getId();
        this.descripcion = actividadInterface.getDescripcion();
        this.fechaInicio = actividadInterface.getFecha_Inicio();
        this.fechaFin = actividadInterface.getFecha_Fin();
        this.fechaFinalizoUser = actividadInterface.getFecha_Finalizo_User();
        this.tipo = actividadInterface.getTipo();
        this.prioridad = actividadInterface.getPrioridad();
        this.status = actividadInterface.getStatus();
        this.isMensual = actividadInterface.getIs_Mensual();
    }

    public void convertDto(EmpresasEntity actividadInterface){
        this.id = actividadInterface.getId();
        this.descripcion = actividadInterface.getDescripcion();
    }

    // public void agregarItemLista(EmpresasEntity empresas){
    //     this.statusActividad.add(empresas);
    // }
} 