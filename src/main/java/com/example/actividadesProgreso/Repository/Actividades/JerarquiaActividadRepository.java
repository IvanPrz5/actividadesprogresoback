package com.example.actividadesProgreso.Repository.Actividades;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actividadesProgreso.Entity.Actividades.JerarquiaActividadEntity;

public interface JerarquiaActividadRepository extends JpaRepository<JerarquiaActividadEntity, Long>{
    List<JerarquiaActividadEntity> findByStatus(Boolean status, Sort Sort);
    List<JerarquiaActividadEntity> findByIdAndStatus(Long id, Boolean status);
}
