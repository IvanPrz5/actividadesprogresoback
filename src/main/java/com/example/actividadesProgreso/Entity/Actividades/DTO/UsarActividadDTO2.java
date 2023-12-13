package com.example.actividadesProgreso.Entity.Actividades.DTO;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.actividadesProgreso.Entity.Actividades.Interface.UsarActividadInterface;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsarActividadDTO2 {
    private Long id;
    private Long idactividad;
    private String inicio;
    private String fin;
    private Map<String, Object> bienActividad = new HashMap<>();
    private Map<String, Object> usuarioAsigno = new HashMap<>();

    public void convertDto(UsarActividadInterface usarActividadInterface){
        this.id = usarActividadInterface.getId();
        this.idactividad = usarActividadInterface.getidactividad();
        this.inicio = usarActividadInterface.getInicio();
        this.fin = usarActividadInterface.getFin();
    }

    /* public void convertDto(EmpresasEntity actividadInterface){
        this.id = actividadInterface.getId();
        this.descripcion = actividadInterface.getDescripcion();
    } */
}
