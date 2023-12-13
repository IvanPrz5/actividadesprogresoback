package com.example.actividadesProgreso.Repository.Actividades;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.ArchivoActividadEntity;

import java.util.List;
public interface ArchivoActividadRepository extends JpaRepository<ArchivoActividadEntity, Long>{
    List<ArchivoActividadEntity> findByStatus(Boolean status);
    List<ArchivoActividadEntity> findByIdActividadAndStatus(ActividadEntity idActividad, Boolean status, Sort sort);
    List<ArchivoActividadEntity> findByIdActividadAndIdArchivoActividadAndStatus(ActividadEntity idActividad, ArchivoActividadEntity idArchivoActividad, Boolean status, Sort sort);
}
