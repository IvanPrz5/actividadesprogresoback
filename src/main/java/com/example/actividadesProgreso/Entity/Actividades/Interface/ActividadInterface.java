package com.example.actividadesProgreso.Entity.Actividades.Interface;

public interface ActividadInterface {
    Long getId();
    String getDescripcion();
    String getFecha_Fin();
    String getFecha_Inicio();
    String getFecha_Finalizo_User();
    String getTipo();
    String getPrioridad();
    String getStatus();
    String getIs_Mensual();
    Long getId_Empresa();
    Long getId_Jerarquia_Actividad();
    Long getId_Status_Actividad();
    Long getId_Tipo_Actividad();
    Long getId_Usuario_Asigno();
    Long getId_Usuario_Termino();
    Long getId_Clientes();
}
