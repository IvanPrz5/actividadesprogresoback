package com.example.actividadesProgreso.Repository.Actividades;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.UsarActividadInterface;

import java.util.List;

public interface UsarActividadRepository extends JpaRepository<UsarActividadEntity, Long>{
    
    List<UsarActividadEntity> findByIdActividadAndStatus(ActividadEntity idActividad, Boolean status);

    List<UsarActividadEntity> findByIdBienActividadAndStatus(BienActividadEntity idBienActividad, Boolean status);
    
    @Query(value="SELECT COUNT(*) AS tipo FROM usar_actividad ua WHERE " +
        "((inicio <= CAST(?1 AS DATE) AND id_bien_actividad = ?3 AND fin >= CAST(?2 AS DATE) AND id_bien_actividad = ?3) AND status = true OR " +
        "inicio BETWEEN CAST(?1 AS DATE) AND CAST(?2 AS DATE) AND id_bien_actividad = ?3 AND status = true OR " +
        "fin BETWEEN CAST(?1 AS DATE) AND CAST(?2 AS DATE) AND id_bien_actividad = ?3 AND status = true)", nativeQuery = true)
    List<Object> fechaDisponible(String inicio, String fin, Long idBienActividad);

    // cambiar a status dinamico
    @Query(value="SELECT act.id AS idactividad, act.id_usuario_asigno, act.status, usact.id, usact.inicio, usact.fin, usact.id_bien_actividad FROM actividad act " + 
    "INNER JOIN usuario_actividad ua ON act.id = ua.id_actividad " + 
    "INNER JOIN usar_actividad usact ON act.id = usact.id_actividad " + 
    "WHERE ua.id_usuario = ?1 AND act.id_empresa = ?2 AND ua.user_act_status = true AND usact.status = true ORDER BY act.id ASC;", nativeQuery = true)
    List<UsarActividadInterface> getRecursosByUsuario(Long idUsuario, Long idEmpresa);

    @Query(value="SELECT COUNT(*) FROM actividad act " + 
    "INNER JOIN usuario_actividad ua ON act.id = ua.id_actividad " + 
    "INNER JOIN usar_actividad usact ON act.id = usact.id_actividad " + 
    "WHERE ua.id_usuario = ?1 AND act.id_empresa = ?2 AND ua.user_act_status = true AND usact.status = true", nativeQuery = true)
    List<String> getTotalRecursos(Long idUsuario, Long idEmpresa);
    
}
