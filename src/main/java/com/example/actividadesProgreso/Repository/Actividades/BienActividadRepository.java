package com.example.actividadesProgreso.Repository.Actividades;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Actividades.BienActividadEntity;

public interface BienActividadRepository extends JpaRepository<BienActividadEntity, Long>{
    List<BienActividadEntity> findByStatus(Boolean status, Sort sort);

    // Revisar aqui se modifico la tabla
    @Query(value="SELECT * FROM bien_actividad WHERE id NOT IN (SELECT id_bien_Actividad FROM usar_actividad WHERE id_actividad = ?1 AND status = true) AND (id_empresa = ?2 OR id_empresa IS NULL)", nativeQuery = true)
    List<BienActividadEntity> findByIdActividadAndStatus(Long id, Long idEmpresa);

    List<BienActividadEntity> findByIdAndStatus(Long id, Boolean status);

    @Query(value="SELECT * FROM bien_actividad WHERE id_empresa = ?1 ORDER BY id;", nativeQuery = true)
    List<BienActividadEntity> findByIdEmpresaQuery(Long idEmpresa);

    @Query(value="SELECT * FROM bien_actividad WHERE id_empresa = ?1 OR id_empresa IS NULL ORDER BY id;", nativeQuery = true)
    List<BienActividadEntity> findByIdEmpresaAndNullQuery(Long idEmpresa);
}

// 3, 5, 2