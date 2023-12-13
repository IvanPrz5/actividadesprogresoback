package com.example.actividadesProgreso.Repository.Actividades;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Actividades.NotasActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.NotasActividadInterface;
public interface NotasActividadRepository extends JpaRepository<NotasActividadEntity, Long>{
    List<NotasActividadEntity> findByStatus(Boolean status);
    // List<NotasActividadEntity> findByIdActividadAndIdUsuarioAndStatus(ActividadEntity idActividad, UsuariosEntity idUsuario, Boolean status);
    @Query(value="SELECT na.id, na.descripcion, na.id_usuario, u.nombre, u.ap_paterno FROM notas_actividad na INNER JOIN usuarios u ON na.id_usuario = u.id WHERE na.id_actividad = ? AND na.status = true ORDER BY na.id;\r\n" + //
            "", nativeQuery = true)
    List<NotasActividadInterface> findByIdActividadAndIdUsuario(Long idActividad);
}
