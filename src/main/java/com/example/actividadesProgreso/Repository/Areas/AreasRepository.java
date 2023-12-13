package com.example.actividadesProgreso.Repository.Areas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Areas.AreasEntity;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.IdAndDescripcionInterface;

public interface AreasRepository extends JpaRepository<AreasEntity, Long>{
    @Query(value="SELECT r.id, r.descripcion FROM areas r INNER JOIN users_empresas_and_areas uea ON r.id = uea.id_area WHERE id_usuario = ?1 GROUP BY r.id, r.descripcion", nativeQuery = true)
    List<IdAndDescripcionInterface> getAreas(Long id);
}