package com.example.actividadesProgreso.Repository.Actividades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsuarioActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.UsuarioActividadInterface;

import java.util.List;
public interface UsuarioActividadRepository extends JpaRepository<UsuarioActividadEntity, Long>{

    List<UsuarioActividadEntity> findByIdActividadAndUserActStatus(ActividadEntity idActividad, Boolean status);
    @Query(value="SELECT u.id, u.icon, CONCAT(u.nombre, ' ', u.ap_paterno) AS descripcion, ua.id_ua FROM usuarios u INNER JOIN " + 
    "usuario_actividad ua ON u.id = ua.id_usuario WHERE id_actividad = ?1 AND ua.user_act_status = true ORDER BY u.id", nativeQuery = true)
    List<UsuarioActividadInterface> findByIdActividadQuery(Long id);

    @Query(value="INSERT INTO usuario_actividad WHERE id_actividad = ?2 AND id_ua = ?3", nativeQuery = true)
    List<UsuarioActividadEntity> editRegisterQuery(Long idActividad, Long idUserAct);

    @Query(value="UPDATE usuario_actividad SET user_act_status = false WHERE id_actividad = ?1 AND id_usuario = ?2", nativeQuery = true)
    List<UsuarioActividadEntity> editStatusQuery(Long idActividad, Long idUsuario);
}
