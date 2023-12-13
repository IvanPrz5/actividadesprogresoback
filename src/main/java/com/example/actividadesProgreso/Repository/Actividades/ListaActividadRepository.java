package com.example.actividadesProgreso.Repository.Actividades;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actividadesProgreso.Entity.Actividades.ListaActividadEntity;

public interface ListaActividadRepository extends JpaRepository<ListaActividadEntity, Long>{
    List<ListaActividadEntity> findByStatus(Boolean status);
}
