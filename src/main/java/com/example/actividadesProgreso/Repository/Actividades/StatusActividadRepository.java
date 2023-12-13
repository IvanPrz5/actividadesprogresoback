package com.example.actividadesProgreso.Repository.Actividades;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actividadesProgreso.Entity.Actividades.StatusActividadEntity;

public interface StatusActividadRepository extends JpaRepository<StatusActividadEntity, Long>{
    List<StatusActividadEntity> findByStatus(Boolean status);
    List<StatusActividadEntity> findByIdAndStatus(Long id, Boolean status);
}
