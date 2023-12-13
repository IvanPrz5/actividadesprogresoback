package com.example.actividadesProgreso.Repository.Actividades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.Interface.ActividadInterface;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ActividadRepository extends JpaRepository<ActividadEntity, Long>{
    List<ActividadEntity> findByStatus(Boolean status);
    List<ActividadEntity> findByIdAndStatus(Long id, Boolean status);

    @Query(value="SELECT COUNT(*) FROM (SELECT *, 'TERMINAN HOY' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
    "WHERE CAST(current_date AS date) = CAST(act.fecha_fin AS DATE) AND usact.id_usuario = ?1 AND act.status = true AND act.id_empresa = ?2 " +
    "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
    "WHERE CAST(current_date AS date) > CAST(act.fecha_fin AS DATE) AND usact.id_usuario = ?1 AND act.status = true AND act.id_empresa = ?2 " +
    ") AS datos;", nativeQuery = true)
    List<String> cuentaActividades(Long idUsuario, Long idEmpresa);

    @Query(value="SELECT * FROM (SELECT *, 'TERMINAN HOY' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
    "WHERE CAST(current_date AS date) = CAST(act.fecha_fin AS DATE) AND usact.id_usuario = ?1 AND act.status = true AND act.id_empresa = ?2 " +
    "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
    "WHERE CAST(current_date AS date) > CAST(act.fecha_fin AS DATE) AND usact.id_usuario = ?1 AND act.status = true AND act.id_empresa = ?2 " +
    ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> actividadTerminanHoyAndVencidas(Long idUsuario, Long idEmpresa);

    @Query(value="SELECT * FROM (SELECT *, 'TERMINAN HOY' AS tipo, 1 AS prioridad FROM actividad WHERE CAST(current_date AS date) = CAST(fecha_fin AS DATE) " +
    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad WHERE CAST(current_date AS date) > CAST(fecha_fin AS DATE) " +
    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 UNION SELECT *, 'INICIAN HOY' AS tipo, 2 AS prioridad FROM actividad WHERE CAST(current_date AS date) = CAST(fecha_inicio AS DATE) " +
    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 AND CAST(current_date AS date ) <> CAST(fecha_fin AS DATE) " +
    "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad WHERE CAST(current_date as date ) BETWEEN CAST(fecha_inicio AS DATE) AND CAST(fecha_fin AS DATE) " +
    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 AND CAST(current_date AS date ) <> CAST(fecha_fin AS DATE)  AND CAST(current_date as date ) <> CAST(fecha_inicio as date ) " +
    "UNION SELECT *, 'PROXIMAMENTE' AS tipo, 4 AS prioridad FROM actividad WHERE CAST(current_date AS date) < CAST(fecha_inicio AS DATE) " + 
    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2) AS datos ORDER BY prioridad", nativeQuery = true)
    List<ActividadInterface> consultaAsigneDiaria(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value="SELECT * FROM (SELECT *, 'TERMINAN ESTA SEMANA' AS tipo, 1 AS prioridad FROM actividad " +
	    "WHERE actividad.fecha_fin BETWEEN (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1) AND " +
	    "(CAST (current_date as date) + (7 - CAST (date_part('DOW',  current_date) AS integer))) " +
	    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 " +
	    "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad " +
	    "WHERE (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1) > " +
	    "CAST (fecha_fin AS DATE) AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 " +
        "UNION SELECT *, 'INICIAN ESTA SEMANA' AS tipo, 2 AS prioridad FROM actividad " +
	    "WHERE actividad.fecha_inicio BETWEEN (CAST (current_date as date) - (CAST (date_part('DOW', current_date) AS integer)) + 1) " + 
        "AND (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer))) AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 AND id " +
	    "NOT IN (SELECT id FROM actividad WHERE actividad.fecha_fin BETWEEN (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1) " + 
	    "and (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer))) " +
	    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2) AND status IN (?3) AND id_empresa = ?2 " + 
	    "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad " +
        "WHERE actividad.fecha_inicio < (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer)) + 1) " + 
	    "AND actividad.fecha_fin > (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer))) " +
	    "AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2) AS datos ORDER BY prioridad", nativeQuery = true)
    List<ActividadInterface> consultaAsigneSemanal(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value="SELECT * FROM (SELECT *, 'TERMINAN ESTE MES' AS tipo, 1 AS prioridad FROM actividad WHERE " + 
        "EXTRACT (MONTH FROM actividad.fecha_fin) = EXTRACT (MONTH FROM current_date) AND EXTRACT (MONTH FROM actividad.fecha_inicio) != EXTRACT (MONTH FROM current_date) AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 " + 
        "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad WHERE EXTRACT (MONTH FROM actividad.fecha_fin) < EXTRACT (MONTH FROM current_date) AND  " + 
        "id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 UNION SELECT *, 'INICIAN ESTA MES ' AS tipo, 2  AS prioridad FROM actividad WHERE " + 
        "EXTRACT (MONTH FROM actividad.fecha_inicio) = EXTRACT (MONTH FROM  current_date) AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2 " + 
        "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad WHERE EXTRACT (MONTH FROM actividad.fecha_inicio) < EXTRACT (MONTH FROM current_date)  " + 
        "AND EXTRACT (MONTH FROM actividad.fecha_fin) > EXTRACT (MONTH FROM  current_date) AND id_usuario_asigno = ?1 AND status IN (?3) AND id_empresa = ?2) " + 
        "AS  datos ORDER BY prioridad", nativeQuery = true)
    List<ActividadInterface> consultaAsigneMensual(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value="SELECT * FROM ( " +
        "SELECT *, 'TERMINAN HOY' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
                "WHERE CAST(current_date AS date) = CAST(act.fecha_fin AS DATE) " +
                "AND usact.id_usuario = ?1  AND act.status = ?3 AND act.id_empresa = ?2 " +
        "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
                "WHERE CAST(current_date AS date) > CAST(act.fecha_fin AS DATE) " +
                "AND usact.id_usuario = ?1  AND act.status = ?3 AND act.id_empresa = ?2 " +
        "UNION SELECT *, 'INICIAN HOY' AS tipo, 2 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
                "WHERE CAST(current_date AS date) = CAST(act.fecha_inicio AS DATE) " +
                "AND usact.id_usuario = ?1  AND act.status = ?3 AND act.id_empresa = ?2 " +
                "AND CAST(current_date AS date ) <> CAST(fecha_fin AS DATE) " +
        "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
                "WHERE CAST(current_date as date ) BETWEEN CAST(act.fecha_inicio AS DATE) AND CAST(act.fecha_fin AS DATE) " +
                "AND usact.id_usuario = ?1  AND act.status = ?3 AND act.id_empresa = ?2 " +
                "AND CAST(current_date AS date ) <> CAST(act.fecha_fin AS DATE) AND CAST(current_date as date ) <> CAST(fecha_inicio as date) " +
        "UNION SELECT *, 'PROXIMAMENTE' AS tipo, 4 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
                "WHERE CAST(current_date AS date) < CAST(fecha_inicio AS DATE) " +
                "AND usact.id_usuario = ?1  AND act.status = ?3 AND act.id_empresa = ?2 " +
    ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> consultaAsignaronDiaria(Long idUsuario, Long idEmpresa, Boolean status);
    
    @Query(value="SELECT * FROM ( " +
            "SELECT *, 'TERMINAN ESTA SEMANA' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " + 
                "WHERE act.fecha_fin BETWEEN (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1)  " + 
                "AND (CAST (current_date as date) + (7 - CAST (date_part('DOW',  current_date) AS integer))) " + 
                "AND usact.id_usuario = ?1 AND act.status IN (?3) AND act.id_empresa = ?2 " + 
            "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " + 
                "WHERE (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1) > CAST (act.fecha_fin AS DATE)  " + 
                "AND usact.id_usuario = ?1 AND act.status IN (?3) AND act.id_empresa = ?2  " + 
            "UNION SELECT *, 'INICIAN ESTA SEMANA' AS tipo, 2 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " + 
                "WHERE act.fecha_inicio BETWEEN (CAST (current_date as date) - (CAST (date_part('DOW', current_date) AS integer)) + 1)  " + 
                "AND (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer))) " + 
                "AND usact.id_usuario = ?1 AND act.status IN (?3) AND act.id_empresa = ?2 " + 
                "AND id NOT IN (SELECT id FROM actividad WHERE act.fecha_fin BETWEEN (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1)   " + 
                "and (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer)))  " + 
                "AND usact.id_usuario = ?1 AND act.status IN (?3) AND act.id_empresa = ?2) " + 
            "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " + 
                "WHERE act.fecha_inicio < (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer)) + 1)  " + 
                "AND act.fecha_fin > (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer)))  " + 
                "AND usact.id_usuario = ?1 AND act.status IN (?3) AND act.id_empresa = ?2 " + 
            ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> consultaAsignaronSemanal(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value="SELECT * FROM ( " +
        "SELECT *, 'TERMINAN ESTE MES' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_fin) = EXTRACT (MONTH FROM current_date) " +
            "AND EXTRACT (MONTH FROM act.fecha_inicio) != EXTRACT (MONTH FROM current_date) " +
            "AND usact.id_usuario = ?1 AND act.status = ?3 AND act.id_empresa = ?2  " +
        "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_fin) < EXTRACT (MONTH FROM current_date) " + 
            "AND usact.id_usuario = ?1 AND act.status = ?3 AND act.id_empresa = ?2  " +
        "UNION SELECT *, 'INICIAN ESTA MES ' AS tipo, 2  AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_inicio) = EXTRACT (MONTH FROM current_date)  " +
            "AND usact.id_usuario = ?1 AND act.status = ?3 AND act.id_empresa = ?2  " + 
        "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_inicio) < EXTRACT (MONTH FROM current_date)  " +
            "AND EXTRACT (MONTH FROM act.fecha_fin) > EXTRACT (MONTH FROM  current_date)   " + 
            "AND usact.id_usuario = ?1 AND act.status = ?3 AND act.id_empresa = ?2 " +
    ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> consultaAsignaronMensual(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value=" SELECT * FROM ( " +
        "SELECT *, 'TERMINAN HOY' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
                "WHERE CAST(current_date AS date) = CAST(act.fecha_fin AS DATE) " +
                "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
         "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
                "WHERE CAST(current_date AS date) > CAST(act.fecha_fin AS DATE)  " +
                "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
         "UNION SELECT *, 'INICIAN HOY' AS tipo, 2 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
                "WHERE CAST(current_date AS date) = CAST(act.fecha_inicio AS DATE)  " +
                "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
                "AND CAST(current_date AS date ) <> CAST(fecha_fin AS DATE)  " +
         "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
                "WHERE CAST(current_date as date ) BETWEEN CAST(act.fecha_inicio AS DATE) AND CAST(act.fecha_fin AS DATE)  " +
                "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
                "AND CAST(current_date AS date ) <> CAST(act.fecha_fin AS DATE) AND CAST(current_date as date ) <> CAST(fecha_inicio as date) " +
         "UNION SELECT *, 'PROXIMAMENTE' AS tipo, 4 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
                "WHERE CAST(current_date AS date) < CAST(fecha_inicio AS DATE) " +
                "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
    ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> consultaDiariaTodas(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value = "SELECT * FROM ( " +
        "SELECT *, 'TERMINAN ESTA SEMANA' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE act.fecha_fin BETWEEN (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1)  " +
            "AND (CAST (current_date as date) + (7 - CAST (date_part('DOW',  current_date) AS integer))) " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
        "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1) > CAST (act.fecha_fin AS DATE)  " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
        "UNION SELECT *, 'INICIAN ESTA SEMANA' AS tipo, 2 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " +
            "WHERE act.fecha_inicio BETWEEN (CAST (current_date as date) - (CAST (date_part('DOW', current_date) AS integer)) + 1)  " +
            "AND (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer)))  " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
            "AND id NOT IN (SELECT id FROM actividad WHERE act.fecha_fin BETWEEN (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer )) + 1)   " +
            "and (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer)))  " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 )  " +
        "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE act.fecha_inicio < (CAST (current_date AS date) - (CAST (date_part('DOW', current_date) AS integer)) + 1)  " +
            "AND act.fecha_fin > (CAST (current_date AS date) + (7 - CAST (date_part('DOW', current_date) AS integer)))  " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
    ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> consultaSemanalTodas(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value = "SELECT * FROM ( " +
        "SELECT *, 'TERMINAN ESTE MES' AS tipo, 1 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_fin) = EXTRACT (MONTH FROM current_date) " +
            "AND EXTRACT (MONTH FROM act.fecha_inicio) != EXTRACT (MONTH FROM current_date) " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
        "UNION SELECT *, 'CON RETRASO' AS tipo, 0 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_fin) < EXTRACT (MONTH FROM current_date) " + 
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
        "UNION SELECT *, 'INICIAN ESTA MES ' AS tipo, 2  AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_inicio) = EXTRACT (MONTH FROM current_date)  " +
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " + 
        "UNION SELECT *, 'ASIGNADA' AS tipo, 3 AS prioridad FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad  " +
            "WHERE EXTRACT (MONTH FROM act.fecha_inicio) < EXTRACT (MONTH FROM current_date)  " +
            "AND EXTRACT (MONTH FROM act.fecha_fin) > EXTRACT (MONTH FROM  current_date)   " + 
            "AND (usact.id_usuario = ?1 OR act.id_usuario_asigno = ?1 ) AND usact.user_act_status = true AND act.status = ?3 AND act.id_empresa = ?2 " +
    ") AS datos ORDER BY prioridad;", nativeQuery = true)
    List<ActividadInterface> consultaMensualTodas(Long idUsuario, Long idEmpresa, Boolean status);

    @Query(value="SELECT * FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " + 
            "WHERE usact.id_usuario = ?1 AND act.id_empresa = ?3 AND act.status = true " +
            "AND (DATE(act.fecha_inicio) = CAST(?2 AS DATE) OR DATE(act.fecha_fin) = CAST(?2 AS DATE));", nativeQuery = true)
    List<ActividadInterface> getActividadesPorDia(Long idUsuario, String fechaInicio, Long idEmpresa);

    @Query(value="SELECT * FROM actividad act INNER JOIN usuario_actividad usact ON act.id = usact.id_actividad " + 
            "WHERE usact.id_usuario = ?1 AND act.id_empresa = ?2 AND DATE(act.fecha_inicio) >= CAST(?3 AS DATE) AND DATE(act.fecha_fin) <= CAST(?4 AS DATE) " + 
            "AND act.status = true", nativeQuery = true)
    List<ActividadInterface> getActividadesEnMes(Long idUsuario, Long idEmpresa, String fechaInicio, String fechaFin);

    @Modifying
    @Query(value = "INSERT INTO public.actividad " + 
    "descripcion, fecha_fin, fecha_inicio, status, id_empresa, id_jerarquia_actividad, id_status_actividad, " + 
    "id_tipo_actividad, id_usuario_asigno, is_mensual) " +
    "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
    List<ActividadEntity> insertIntoActividades(String descripcion, LocalDateTime fechaFin, LocalDateTime fechaInicio, Boolean status, Long idEmpresa,
        Long idJerarquiaActividad, Long idStatusActividad, Long idTipoActividad, Long idUsuarioAsigno, Boolean isMensual);
}
