package com.example.actividadesProgreso.Repository.Empresas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.EmpresasInterface;

public interface EmpresasRepository extends JpaRepository<EmpresasEntity, Long>{
    @Query(value="SELECT e.id, e.descripcion, e.bienes_unicos FROM empresas e INNER JOIN users_empresas_and_areas uea ON e.id = uea.id_empresa WHERE id_usuario = ?1 GROUP BY e.id, e.descripcion", nativeQuery = true)
    List<EmpresasInterface> getEmpresas(Long id);

    List<EmpresasEntity> findByIdAndStatus(Long id, Boolean status);

    @Query(value="SELECT bienes_unicos FROM empresas WHERE id = ?1", nativeQuery = true)
    List<Object> getBienesUnicos(Long id);
}
