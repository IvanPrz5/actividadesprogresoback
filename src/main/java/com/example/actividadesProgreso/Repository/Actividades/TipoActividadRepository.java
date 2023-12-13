package com.example.actividadesProgreso.Repository.Actividades;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actividadesProgreso.Entity.Actividades.TipoActividadEntity;
import java.util.List;


public interface TipoActividadRepository extends JpaRepository<TipoActividadEntity, Long>{
    Optional<TipoActividadEntity> findById(Long id);
    List<TipoActividadEntity> findByStatus(Boolean status, Sort sort);
    List<TipoActividadEntity> findByIdAndStatus(Long id, Boolean status);
}
